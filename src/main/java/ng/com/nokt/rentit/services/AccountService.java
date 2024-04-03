package ng.com.nokt.rentit.services;

import ng.com.nokt.rentit.entities.Account;
import ng.com.nokt.rentit.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
