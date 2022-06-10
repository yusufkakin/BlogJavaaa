package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.repositories.UserRepository;
import com.hustlers.springboot.blog.model.Authority;
import com.hustlers.springboot.blog.model.BlogUser;
import com.hustlers.springboot.blog.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private NotificationService notifyService;
    private final PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private PostService postService;

    public UserServiceImpl(UserRepository userRepository, NotificationService notifyService,
                           PasswordEncoder passwordEncoder, AuthorityService authorityService, PostService postService) {
        this.userRepository = userRepository;
        this.notifyService = notifyService;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.postService = postService;
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        User user = null;
        if(result.isPresent()){
            user = result.get();
        }else{
            notifyService.addErrorMessage("Did not find user id - " + id);
        }
        return user;
    }

    @Override
    public User create(BlogUser blogUser) {
        User user = new User();
        user.setUsername(blogUser.getUsername());
        user.setPasswordHash(passwordEncoder.encode(blogUser.getPassword()));
        user.setFullName(blogUser.getFullName());
        user.setEnabled(true);

        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        Set<Authority> authorityList = new HashSet<>();
        authorityList.add(authority);
        user.setAuthorities(authorityList);
        userRepository.save(user);
        return user;
    }

    @Override
    public void edit(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {

        this.userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String userName) {
        for(User user :  userRepository.findAll()){
            if(user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }
}
