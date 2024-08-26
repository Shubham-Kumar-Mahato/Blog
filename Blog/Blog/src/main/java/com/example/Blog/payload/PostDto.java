package com.example.Blog.payload;




import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.Blog.Entity.Category;
import com.example.Blog.Entity.User;



public class PostDto {

	
private int postId;
	public int getPostId() {
	return postId;
}
public void setPostId(int postId) {
	this.postId = postId;
}
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;

	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<CommentDto>();



public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public PostDto() {
		
	}
	
	




	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
 	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public PostDto(int postId, String title, String content,String imageName,Date addedDate, CategoryDto category, UserDto user,Set<CommentDto> comments) {
		super();
		this.postId=postId;
		this.title = title;
		this.content = content;
		this.imageName=imageName;
		this.addedDate=addedDate;
		this.category = category;
		this.user = user;
		this.comments=comments;
	}
	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", addedDate=" + addedDate + ", category=" + category + ", user=" + user + ", comments=" + comments
				+ "]";
	}

	
	
}
