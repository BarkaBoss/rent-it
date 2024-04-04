package ng.com.nokt.rentit.config;

import ng.com.nokt.rentit.entities.Authority;
import ng.com.nokt.rentit.entities.Post;
import ng.com.nokt.rentit.entities.Account;
import ng.com.nokt.rentit.services.AuthorityService;
import ng.com.nokt.rentit.services.PostService;
import ng.com.nokt.rentit.services.AccountService;
import ng.com.nokt.rentit.utils.constants.Privileges;
import ng.com.nokt.rentit.utils.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for(Privileges auth : Privileges.values()){
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivilege());
            authorityService.save(authority);
        }

        List<Post> posts = postService.getAllPost();

        Account account = new Account();
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();

        account.setEmail("a@f.com");
        account.setPassword("password27");
        account.setFirstname("Abigail");
        account.setLastname("Fori");

        account1.setEmail("l@s.com");
        account1.setPassword("password27");
        account1.setFirstname("Lele");
        account1.setLastname("Simpah");
        account1.setRole(Roles.ADMIN.getRole());

        account2.setEmail("j@f.com");
        account2.setPassword("password27");
        account2.setFirstname("Joseph");
        account2.setLastname("Fori");
        account2.setRole(Roles.EDITOR.getRole());

        account3.setEmail("dt@f.com");
        account3.setPassword("password27");
        account3.setFirstname("Tat");
        account3.setLastname("Fori");
        account3.setRole(Roles.EDITOR.getRole());
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privileges.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privileges.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        account3.setAuthorities(authorities);

        accountService.save(account);
        accountService.save(account1);
        accountService.save(account2);
        accountService.save(account3);


        if(posts.size() ==  0){
            Post post01 = new Post();
            post01.setTitle("Sample Post");
            post01.setBody("Some Long Text Here");
            post01.setAccount(account);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Example Post");
            post02.setBody("Some Really Long Text Here");
            post02.setAccount(account);
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("Xample Post");
            post03.setBody("Some Really Really  Long Text Here");
            post03.setAccount(account1);
            postService.save(post03);
        }
    }
}
