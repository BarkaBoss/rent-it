package ng.com.nokt.rentit.controller;

import ng.com.nokt.rentit.entities.Account;
import ng.com.nokt.rentit.entities.Post;
import ng.com.nokt.rentit.services.AccountService;
import ng.com.nokt.rentit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home(Model model){
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable Long id){
        Post post = postService.getPostById(id).get();
        model.addAttribute("post", post);
        return "details";
    }
}
