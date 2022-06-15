package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.services.PostService;
import com.hustlers.springboot.blog.services.UserService;
import com.hustlers.springboot.blog.model.Post;
import com.hustlers.springboot.blog.model.User;
import com.hustlers.springboot.blog.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
public class PostsController {
    private PostService postService;
    private NotificationService notifyService;
    private UserService userService;

    @Autowired
    public PostsController(PostService thePostService, NotificationService theNotifyService, UserService userService) {
        this.postService = thePostService;
        this.notifyService = theNotifyService;
        this.userService = userService;
    }

    //    Can find post by ID
    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Post post = postService.findById(id);

        if(post == null){
            notifyService.addErrorMessage("Cannot find post with id #" + id);
            return "redirect:/";
        }
        model.addAttribute("post",post);
        return "posts/view";
    }

    //    Will show all post once user clicks on "Posts" page.
    @GetMapping("/posts")
    public String listUserPosts(Model theModel){

        Set<Post> posts = postService.findAll();


        theModel.addAttribute("posts", posts);

        return "posts/index";
    }

    //    Allows user to create a post once they are logged in. They will need to click on "Create Post"
    @GetMapping("/posts/create")
    public String createPost(Model theModel){

        Post post = new Post();

        theModel.addAttribute("post", post);
        return "posts/create";
    }

    //    Allows the user to update the post that they have created
    @GetMapping("/posts/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("postId") Long theId, Model theModel){
        Post post = postService.findById(theId);
        theModel.addAttribute("post", post);
        return "posts/create";
    }

    //    Allows user to save the post they have created and updated.
    @PostMapping("/posts/save")
    public String savePost(@ModelAttribute("post") Post post){
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        post.setAuthor(user);
        postService.create_edit(post);
        notifyService.addInfoMessage("Post created successfully");
        return "redirect:/posts";
    }

    //    Allows the user to delete the post they have created.
    @GetMapping("/posts/delete")
    public String showFormForDelete(@RequestParam("postId") Long theId, RedirectAttributes redirectAttributes) {

        postService.deleteById(theId);

        redirectAttributes.addFlashAttribute("message", "deleted successfully");

        return "redirect:/users/post";
    }
}