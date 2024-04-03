package ng.com.nokt.rentit.config;

import ng.com.nokt.rentit.entities.Post;
import ng.com.nokt.rentit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllPost();

        if(posts.size() ==  0){
            Post post01 = new Post();
            post01.setTitle("Sample Post");
            post01.setBody("Some Long Text Here");
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Example Post");
            post02.setBody("Some Really Long Text Here");
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("Xample Post");
            post03.setBody("Some Really Really Long Text Here");
            postService.save(post03);
        }
    }
}
