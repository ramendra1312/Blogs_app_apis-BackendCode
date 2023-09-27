package com.turbo.blog.services.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turbo.blog.entities.Comment;
import com.turbo.blog.entities.Post;
import com.turbo.blog.exceptions.ResourcesNotFoundException;
import com.turbo.blog.payloads.CommentDto;
import com.turbo.blog.repositories.CommentRepo;
import com.turbo.blog.repositories.PostRepo;
import com.turbo.blog.services.CommentServices;
@Service
public class CommentServicesImp implements CommentServices {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourcesNotFoundException("Post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return  this.modelMapper.map(savedComment, commentDto.getClass());

	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourcesNotFoundException("Comment", "commentId", commentId));
		commentRepo.deleteById(commentId);

	}

}
