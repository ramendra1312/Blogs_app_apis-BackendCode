	package com.turbo.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turbo.blog.entities.Category;
import com.turbo.blog.entities.Post;
import com.turbo.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	List<Post>findByTitleContaining(String title);

}
