package ng.com.nokt.rentit.services;

import ng.com.nokt.rentit.entities.Account;
import ng.com.nokt.rentit.entities.Authority;
import ng.com.nokt.rentit.repositories.AccountRepository;
import ng.com.nokt.rentit.utils.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if(account.getRole() == null){
            account.setRole(Roles.USER.getRole());
        }
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByEmailIgnoreCase(email);
        if(!optionalAccount.isPresent()){
            throw new RuntimeException("User not  found");
        }
        Account account = optionalAccount.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole()));

        for (Authority _auth: account.getAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(_auth.getName()));
        }
        return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }
}
