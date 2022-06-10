package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.services.AuthorityService;
import com.hustlers.springboot.blog.services.NotificationService;
import com.hustlers.springboot.blog.services.UserService;
import com.hustlers.springboot.blog.model.Authority;
import com.hustlers.springboot.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private AuthorityService authorityService;
    private NotificationService notifyService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(PostsController.class);

    @Autowired
    public AdminController(AuthorityService authorityService, NotificationService theNotifyService, UserService userService) {
        this.authorityService = authorityService;
        this.notifyService = theNotifyService;
        this.userService = userService;
    }

    @GetMapping("/Admin/change")
    public String changeRights(@RequestParam("userId") Long theId, Model theModel) {
        User user = userService.findById(theId);
        theModel.addAttribute("user", user);

        List<String> rights = new ArrayList<>();
        rights.add("MANAGER");
        rights.add("ADMIN");

        theModel.addAttribute("authorityList", rights);

        return "admin/changeRight";
    }

    @PostMapping("/Admin/confirmChange")
    public String confirmRightChange(@ModelAttribute("user") User user,
                                     @RequestParam("chosen") String chosenAuthority) {

        logger.info(chosenAuthority + "  username  " + user.getUsername());

        Set<Authority> userAuthority = authorityService.findByUsername(user.getUsername());
        for (Authority authority : userAuthority) {
            if (authority.getAuthority().equals("ROLE_" + chosenAuthority)) {
                notifyService.addInfoMessage("User Already has selected right");
                return "redirect:/users";
            }
        }
        userAuthority.add(new Authority(user.getUsername(), "ROLE_" + chosenAuthority));
        user.setAuthorities(userAuthority);

        userService.edit(user);
        notifyService.addInfoMessage("User rights modified Successfully");
        return "redirect:/users";
    }
}
