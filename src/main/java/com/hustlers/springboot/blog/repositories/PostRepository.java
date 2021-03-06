package com.hustlers.springboot.blog.repositories;

import com.hustlers.springboot.blog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

// This shows the the last five post that have been created and is order by the date the post
// were created in order.

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p from Post p left join fetch p.author order by p.date desc ")
    List<Post> findLatest5Posts(Pageable pageable);

    Set<Post> findByOrderByDateAsc();
}
