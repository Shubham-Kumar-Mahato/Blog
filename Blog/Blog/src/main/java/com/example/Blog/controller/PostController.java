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

import com.example.Blog.Service.PostService;
import com.example.Blog.payload.ApiResponse;
import com.example.Blog.payload.PostDto;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPosts(
			@RequestBody PostDto postDto,
			@PathVariable int categoryId,
			@PathVariable int userId
			){
		PostDto postDto2=this.postService.createPost(postDto, categoryId, userId);
		
		return new  ResponseEntity<PostDto>(postDto2,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId){
		PostDto postDto2=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto2,HttpStatus.OK);
	}
	
	@GetMapping("/category/posts/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
		List<PostDto> post=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	@GetMapping("/users/posts/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		List<PostDto> postDtos=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> postDtos=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId){
		
		 this.postService.deletePost(postId);
		 return new ResponseEntity<>(new ApiResponse("Post deletd successfully",true),HttpStatus.OK);
		
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	
}
