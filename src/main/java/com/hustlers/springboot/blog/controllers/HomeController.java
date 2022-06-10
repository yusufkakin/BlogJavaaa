package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.services.PostService;
import com.hustlers.springboot.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private PostService postService;

    @Autowired
    public HomeController(PostService thePostService) {
        postService = thePostService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Post> homePosts = postService.homePosts(model);
        model.addAttribute("latest3Posts", homePosts);
        return "index";
    }
}
