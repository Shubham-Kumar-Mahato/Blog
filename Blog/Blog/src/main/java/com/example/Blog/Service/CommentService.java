package com.example.Blog.Service;

import com.example.Blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,int postId);
	void deleteComment(int postId);

}
