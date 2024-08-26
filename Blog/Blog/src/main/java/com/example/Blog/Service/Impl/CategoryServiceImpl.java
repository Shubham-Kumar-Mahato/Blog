package com.example.Blog.Service.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blog.Entity.Category;
import com.example.Blog.Exception.ResourceNotFoundException;
import com.example.Blog.Service.CategoryService;
import com.example.Blog.payload.CategoryDto;
import com.example.Blog.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	
		Category category=this.modelMapper.map(categoryDto, Category.class);
		Category newCategory=this.categoryRepo.save(category);
		CategoryDto categoryDto2= this.modelMapper.map(newCategory, CategoryDto.class);
		return categoryDto2;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory =this.categoryRepo.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
	Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", categoryId));
	
	return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category=this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos=category.stream().map(cat -> this.catToDto(cat)).collect(Collectors.toList());
		return categoryDtos;
	
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));
		this.categoryRepo.delete(category);
		
	}
	public CategoryDto catToDto(Category category) {
		CategoryDto categoryDto=this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
