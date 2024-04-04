package ng.com.nokt.rentit.config;

import ng.com.nokt.rentit.entities.Post;
import ng.com.nokt.rentit.entities.Account;
import ng.com.nokt.rentit.services.PostService;
import ng.com.nokt.rentit.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllPost();

        Account account = new Account();
        Account account1 = new Account();

        account.setEmail("j@d.com");
        account.setPassword("password");
        account.setFirstname("Lele");
        account.setLastname("Simpah");

        account1.setEmail("l@s.com");
        account1.setPassword("password1");
        account1.setFirstname("Daniel");
        account1.setLastname("Fori");

        accountService.save(account);
        accountService.save(account1);


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
