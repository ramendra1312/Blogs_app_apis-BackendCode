package com.turbo.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourcesNotFoundException  extends RuntimeException{

	
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourcesNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with  %s:%s",resourceName,fieldName,fieldName ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
}
