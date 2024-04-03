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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home(Model model){
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Account account){
        accountService.save(account);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model  model){
        return "login";
    }
}
