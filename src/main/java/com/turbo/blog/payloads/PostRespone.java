package com.turbo.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostRespone {

	
	private List<PostDto> content;
//	private int id;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalpage;
	private boolean lastPage;
}
