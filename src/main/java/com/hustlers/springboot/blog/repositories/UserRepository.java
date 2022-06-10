package com.hustlers.springboot.blog.repositories;

import com.hustlers.springboot.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
