package com.turbo.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turbo.blog.entities.Category;
import com.turbo.blog.entities.User;
import com.turbo.blog.payloads.CategoryDto;


public interface CategoryServices {

	// creating the user
	CategoryDto CreateCategory(CategoryDto categoryDto);

	// updating the user

	CategoryDto UpdateCategory(CategoryDto categoryDto, Integer categoryId);

	// deleting the record form the database
	void DeleteCategory(Integer categoryId);

	// getting single user form the database
	CategoryDto GetttinguserformCategory(Integer categoryId);

	// getting all user form the database
	List<CategoryDto> GettingAllCategory();

}
