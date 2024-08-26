package com.example.Blog.Service;

import java.util.List;


import com.example.Blog.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	CategoryDto getCategoryById(int categoryId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(int categoryId);
}
