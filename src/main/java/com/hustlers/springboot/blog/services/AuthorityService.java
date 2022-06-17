package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.model.Authority;
import java.util.Set;

public interface AuthorityService {

    Set<Authority> findAll();
    Set<Authority> findByUsername(String username);
}
