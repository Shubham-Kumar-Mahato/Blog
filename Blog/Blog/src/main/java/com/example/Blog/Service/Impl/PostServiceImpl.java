package com.example.Blog.Service.Impl;

import java.util.Date;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blog.Entity.Category;
import com.example.Blog.Entity.Post;
import com.example.Blog.Entity.User;
import com.example.Blog.Exception.ResourceNotFoundException;
import com.example.Blog.Service.PostService;
import com.example.Blog.payload.PostDto;
import com.example.Blog.repository.CategoryRepo;
import com.example.Blog.repository.PostRepo;
import com.example.Blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,int categoryId,int userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", userId));
		Post post =this.modelMapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		PostDto postsDto=this.modelMapper.map(newPost, PostDto.class);
		
		return postsDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
	
	Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","post id", postId));
	
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImageName(postDto.getImageName());
	 
	Post newPost=this.postRepo.save(post);
	
	return this.modelMapper.map(newPost,PostDto.class);
		
	}

	@Override
	public void deletePost(int postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		
		this.postRepo.delete(post);
		

	}

	@Override
	public List<PostDto> getAllPost() {
	List<Post> post=this.postRepo.findAll();
	List<PostDto>postDtos=post.stream().map((p) -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
	return postDtos;
	
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id", postId));
		
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		
		return postDto;
	}
	
	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {
Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category"," category id", categoryId));
		List<Post> post=this.postRepo.findByCategory(cat);
		
		List<PostDto> postDto= post.stream().map((posts) -> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
		return postDto;
	
	}
	@Override
	public List<PostDto> getPostsByUser(int userId){
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," user Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((p) -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
	return postDtos;	
	}

}
