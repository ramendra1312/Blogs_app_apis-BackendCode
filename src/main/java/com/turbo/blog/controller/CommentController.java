package com.turbo.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turbo.blog.payloads.ApiRespone;
import com.turbo.blog.payloads.CommentDto;
import com.turbo.blog.services.CommentServices;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentServices commentServices;
	
	@PostMapping("post/{postId}/comment")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
		CommentDto createComment = this.commentServices.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("comment/{commentId}")
	public ResponseEntity<ApiRespone>createComment(@PathVariable Integer commentId){
		this.commentServices.deleteComment(commentId);
		return new ResponseEntity<ApiRespone>(new ApiRespone("comment succesfully deleted",true),HttpStatus.OK);
		
	}
	

}
