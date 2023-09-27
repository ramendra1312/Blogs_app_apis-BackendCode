package com.turbo.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4 ,message = "must be greater than 4")
	private String categoryTitle;
	@NotEmpty
	@Size(min =4 , message ="must be greater than 4 letter" )
	private String categoryDescription;

}
