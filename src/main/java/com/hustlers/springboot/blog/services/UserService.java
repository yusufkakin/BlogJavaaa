package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.model.BlogUser;
import com.hustlers.springboot.blog.model.User;

import java.util.Set;

public interface UserService {
    Set<User> findAll();
    User findById(Long id);
    User create(BlogUser blogUser);

    void edit(User user);
    void deleteById(Long id);
    User findByUserName(String userName);
}
