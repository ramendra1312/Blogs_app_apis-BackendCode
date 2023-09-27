package com.turbo.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.turbo.blog.entities.Category;
import com.turbo.blog.entities.Comment;
import com.turbo.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addeddate;
	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comment=new HashSet<>();
}
