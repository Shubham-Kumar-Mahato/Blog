package com.example.Blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blog.Service.CategoryService;
import com.example.Blog.payload.ApiResponse;
import com.example.Blog.payload.CategoryDto;
@RestController
@RequestMapping("/api/category")
public class CategoryServiceController {

	@Autowired
	private CategoryService categoryService;
	
       @PostMapping("/")
       public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
	
	CategoryDto categoryDto2=this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
}
@PutMapping("/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable int categoryId) {
	CategoryDto categoryDto2=this.categoryService.updateCategory(categoryDto, categoryId);
	return new ResponseEntity<>(categoryDto2,HttpStatus.OK);
}

@DeleteMapping("/{categoryId}")
public ResponseEntity<?> deleteEntity(@PathVariable int categoryId){
	this.categoryService.deleteCategory(categoryId);
	return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
}
@GetMapping("/")
public ResponseEntity<List<CategoryDto>> getAllCategories(){
	List<CategoryDto> categoryDtos=this.categoryService.getAllCategory();
	return new ResponseEntity<>(categoryDtos,HttpStatus.ACCEPTED);
}
@GetMapping("/{categoryId}")
public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId){
	CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);
	return new  ResponseEntity<CategoryDto>(categoryDto,HttpStatus.ACCEPTED);
	
}
	
}
