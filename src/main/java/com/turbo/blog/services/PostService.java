package com.turbo.blog.services;

import java.util.List;

import com.turbo.blog.entities.Category;
import com.turbo.blog.entities.Post;
import com.turbo.blog.entities.User;
import com.turbo.blog.payloads.PostDto;
import com.turbo.blog.payloads.PostRespone;

public interface PostService {
	
	//for creating the post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//for update the post 
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//for deleting the post
	
	void deletePost(Integer postId);
	
	
	//for getting all Post for database
	
	PostRespone allPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//for getting sing Post from  the database
	
	PostDto singlPostById(Integer postId);
	
	//get all post by category 
	List<PostDto>postBycategory(Integer CategoryId);
	
	//serach the post by keywords
	List<PostDto>searchPost(String  keyword);
	
	
	//get all post by user
	List<PostDto>postByUser(Integer userId);
	
	

}
