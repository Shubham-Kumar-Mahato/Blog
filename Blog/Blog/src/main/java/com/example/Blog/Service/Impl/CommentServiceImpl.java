package com.example.Blog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blog.Entity.Comment;
import com.example.Blog.Entity.Post;
import com.example.Blog.Exception.ResourceNotFoundException;
import com.example.Blog.Service.CommentService;
import com.example.Blog.payload.CommentDto;
import com.example.Blog.repository.CommentRepository;
import com.example.Blog.repository.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto,int postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post notfound", "Post", postId));
		
		
		Comment comments=this.modelMapper.map(commentDto, Comment.class);
		comments.setPost(post);
		
		Comment savedcomment=this.commentRepository.save(comments);
		
		
		return this.modelMapper.map(savedcomment, CommentDto.class);
		
		
	}

	@Override
	public void deleteComment(int commentId) {
		
     Comment comment=this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post notfound", "Post", commentId));
		
     this.commentRepository.delete(comment);

	}

}
