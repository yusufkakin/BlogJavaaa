package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.repositories.AuthorityRepository;
import com.hustlers.springboot.blog.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Set<Authority> findAll() {
        return new HashSet<>(authorityRepository.findAll());
    }

    @Override
    public Set<Authority> findByUsername(String username) {
        return findAll()
                .parallelStream()
                .filter(authority -> authority.getUsername().equals(username))
                .collect(Collectors.toSet());
    }

}
