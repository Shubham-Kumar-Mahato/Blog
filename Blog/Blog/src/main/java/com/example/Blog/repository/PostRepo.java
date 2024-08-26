package com.example.Blog.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blog.Entity.Category;
import com.example.Blog.Entity.Post;
import com.example.Blog.Entity.User;


public interface PostRepo extends JpaRepository<Post, Integer> {
	
//	Post findById(int postId);
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
