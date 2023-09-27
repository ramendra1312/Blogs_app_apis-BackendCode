package com.turbo.blog.payloads;

import com.turbo.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
	
	private int id;
	private String content;
	
	

}
