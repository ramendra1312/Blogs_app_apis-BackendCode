package com.turbo.blog.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.turbo.blog.config.AppConfig;
import com.turbo.blog.entities.Post;
import com.turbo.blog.payloads.ApiRespone;
import com.turbo.blog.payloads.PostDto;
import com.turbo.blog.payloads.PostRespone;
import com.turbo.blog.services.FileService;
import com.turbo.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(("/api/"))
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;

	@Autowired
	private PostService posService;

	@Value("${project.image}")
	private String path;

	// creating the post ,start form controller section
	@PostMapping(("/user/{userId}/category/{categoryId}/post"))
	public ResponseEntity<PostDto> creatPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = this.postService.postByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);

	}

	@GetMapping("/category/{CategoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer CategoryId) {
		List<PostDto> postBycategory = this.postService.postBycategory(CategoryId);
		return new ResponseEntity<List<PostDto>>(postBycategory, HttpStatus.OK);
	}

	@GetMapping("/allPost")
	public ResponseEntity<PostRespone> getallpost(
			@RequestParam(value = "pageNumber", defaultValue = AppConfig.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConfig.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConfig.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConfig.SORT_DIR, required = false) String sortDir

	) {

		PostRespone allPost = this.postService.allPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostRespone>(allPost, HttpStatus.OK);

	}

	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> postByid(@PathVariable Integer postId) {
		PostDto singlPostById = this.postService.singlPostById(postId);
		return new ResponseEntity<PostDto>(singlPostById, HttpStatus.OK);
	}

	@DeleteMapping("post/{postId}")
	public ApiRespone deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiRespone("post has been deleted succefully", true);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword) {
		List<PostDto> searchPost = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);

	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId// for fetching the user id
	) throws IOException {

		PostDto postDto = this.postService.singlPostById(postId);
		String uploadImage = this.fileService.uploadImage(path, image);

		postDto.setImageName(uploadImage);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException {
		InputStream resources = this.fileService.getResources(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resources, response.getOutputStream());
	}
		
}