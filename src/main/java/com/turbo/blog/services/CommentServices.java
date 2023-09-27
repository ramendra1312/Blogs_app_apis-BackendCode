package com.turbo.blog.services;

import com.turbo.blog.payloads.CommentDto;

public interface CommentServices {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
