package com.turbo.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turbo.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
