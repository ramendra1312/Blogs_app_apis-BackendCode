package com.turbo.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turbo.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

}
