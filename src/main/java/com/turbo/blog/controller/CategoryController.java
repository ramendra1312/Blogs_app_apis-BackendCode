package com.turbo.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turbo.blog.entities.Category;
import com.turbo.blog.payloads.ApiRespone;
import com.turbo.blog.payloads.CategoryDto;
import com.turbo.blog.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryServices categoyServices;

	// create
	@PostMapping("/addcategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoyServices.CreateCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

//update the user
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryId,
			@PathVariable Integer catId) {
		CategoryDto updateCategory = this.categoyServices.UpdateCategory(categoryId, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

	}

	// delete
	@DeleteMapping("/{CatId}")
	public ResponseEntity<ApiRespone> deleteCategory(@PathVariable Integer CatId) {
		this.categoyServices.DeleteCategory(CatId);
		return new ResponseEntity<ApiRespone>(new ApiRespone("entered user Id has been deleted", true), HttpStatus.OK);

	}

	// getting
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		CategoryDto getttinguserformCategory = this.categoyServices.GetttinguserformCategory(catId);
		return new ResponseEntity<CategoryDto>(getttinguserformCategory, HttpStatus.OK);
	}
	// getting all category

	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> allCategory(){
		List<CategoryDto> gettingAllCategory = this.categoyServices.GettingAllCategory();
		return ResponseEntity.ok(gettingAllCategory);
		
		
		
		
	}

	//

}
