package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.model.Post;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

public interface PostService {
    Set<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);

    void create_edit(Post post);
    void deleteById(Long id);
    Set<Post> findByAuthor(String username);

    List<Post> homePosts(Model model);
}
