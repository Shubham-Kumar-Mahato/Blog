package com.example.Blog.Service;

import java.util.List;




import com.example.Blog.payload.PostDto;



public interface PostService {

	
	PostDto createPost(PostDto postDto,int categoryId,int userId);
	PostDto updatePost(PostDto postDto,int postId);
	void deletePost(int postId);
	List<PostDto> getAllPost();
	PostDto getPostById(int postId);
	List<PostDto> getPostsByCategory(int categoryId);
	 List<PostDto> getPostsByUser(int userId);
}
