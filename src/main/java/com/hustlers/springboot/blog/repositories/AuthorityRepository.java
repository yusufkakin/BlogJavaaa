package com.hustlers.springboot.blog.repositories;

import com.hustlers.springboot.blog.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
