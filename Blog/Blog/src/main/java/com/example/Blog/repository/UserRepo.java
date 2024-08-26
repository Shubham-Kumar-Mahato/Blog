package com.example.Blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Blog.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String name);

}
