package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.services.NotificationService;
import com.hustlers.springboot.blog.services.PostService;
import com.hustlers.springboot.blog.services.UserService;
import com.hustlers.springboot.blog.model.Post;
import com.hustlers.springboot.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private PostService postService;
    private NotificationService notifyService;

    @Autowired
    public UserController(UserService theUserService, PostService postService, NotificationService theNotifyService) {
        this.userService = theUserService;
        this.postService = postService;
        this.notifyService = theNotifyService;
    }

    @GetMapping("/users")
    public String ListUsers(Model theModel){
        Set<User> users = userService.findAll();
        theModel.addAttribute("users", users);

        return "users/index";
    }

    @GetMapping("/users/post")
    public String listUserPost(Model theModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Set<Post> userPosts =  postService.findByAuthor(username);
        if(userPosts == null ||  userPosts.isEmpty()){
            notifyService.addErrorMessage(username +" has not posted");
            return "redirect:/";
        }
        String some = (String) theModel.asMap().get("message");
        if (some != null) {
            notifyService.addInfoMessage(some);
        }

        theModel.addAttribute("userPosts", userPosts);

        return "posts/userPostIndex";
    }

    @GetMapping("/users/delete")
    public String showFormForDelete(@RequestParam("userId") Long theId){
        userService.deleteById(theId);
        // redirect to /employee/list
        return "redirect:/users";
    }
}
