package com.hustlers.springboot.blog.services;

import com.hustlers.springboot.blog.repositories.PostRepository;
import com.hustlers.springboot.blog.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Primary
@Service
@Slf4j
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    private NotificationService notifyService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public PostServiceImpl(PostRepository postRepo, NotificationService notificationService, @Lazy UserService userService) {
        this.postRepository = postRepo;
        this.notifyService = notificationService;
    }

    @Override
    public Set<Post> findAll() {
        return new HashSet<>(postRepository.findByOrderByDateAsc());
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepository.findLatest5Posts(PageRequest.of(0, 5));
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> result = postRepository.findById(id);
        Post post = null;
        if(result.isPresent()){
            post = result.get();
        }else{
            notifyService.addErrorMessage("Did not find Post id - " + id);
        }

        return post;

    }

    @Override
    public void create_edit(Post post) {
        this.postRepository.save(post);
    }


    @Override
    public void deleteById(Long id) {
        if (!this.findAll().contains(findById(id)) || id <= 0) {
            log.info("id>>>>>>>.. " + id + " size: " + findAll().size());
            notifyService.addErrorMessage("Post not found: " + id);
            return;
        }
        this.postRepository.deleteById(id);
    }


    @Override
    public Set<Post> findByAuthor(String username) {
        return findAll()
                .stream()
                .filter(post -> post.getAuthor().getUsername().equals(username))
                .collect(Collectors.toSet());
    }

    //    This will allow the last five post to show on the home page

    @Override
    public List<Post> homePosts(Model model) {
        List<Post> lastest5Posts = findLatest5();
        model.addAttribute("latest5Posts", lastest5Posts);

        return lastest5Posts.stream().
                limit(3).collect(Collectors.toList());
    }
}
