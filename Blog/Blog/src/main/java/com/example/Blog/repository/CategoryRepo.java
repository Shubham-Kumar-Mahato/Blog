package com.example.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Blog.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
