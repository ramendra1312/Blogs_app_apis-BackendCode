package com.turbo.blog.services.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turbo.blog.entities.Category;
import com.turbo.blog.entities.Post;
import com.turbo.blog.entities.User;
import com.turbo.blog.exceptions.ResourcesNotFoundException;
import com.turbo.blog.payloads.PostDto;
import com.turbo.blog.payloads.PostRespone;
import com.turbo.blog.repositories.CategoryRepo;
import com.turbo.blog.repositories.PostRepo;
import com.turbo.blog.repositories.UserRepo;
import com.turbo.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User ", "userId", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "categoryId", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post save = this.postRepo.save(post);

		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourcesNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatepost = this.postRepo.save(post);

		return this.modelMapper.map(updatepost, postDto.getClass());
	}

	@Override
	public void deletePost(Integer postId) {
		Post findById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourcesNotFoundException("Post", "postId", postId));
		this.postRepo.delete(findById);
	}

	@Override
	public PostRespone allPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		
		
		//we can get the data according requirement asc ,desc 
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		

		// here we are putting pagination...here
		// importing the pagination from Jpa repo
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> findAll = this.postRepo.findAll(p);
		List<Post> allPost = findAll.getContent();

		List<PostDto> all = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostRespone postRespone = new PostRespone();

		postRespone.setContent(all);
		postRespone.setPageNumber(findAll.getNumber());
		postRespone.setPageSize(findAll.getSize());
		postRespone.setTotalElement(findAll.getTotalElements());
		postRespone.setTotalpage(findAll.getTotalPages());
		postRespone.setLastPage(findAll.isLast());

		return postRespone;
	}

	@Override
	public PostDto singlPostById(Integer postId) {
		Post postbyID = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourcesNotFoundException("Posts", "postId", postId));
		PostDto postbyid = this.modelMapper.map(postbyID, PostDto.class);

		return postbyid;
	}

	@Override
	public List<PostDto> postBycategory(Integer CategoryId) {
		Category categroy = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "categoryId", CategoryId));
		List<Post> findByCategory = this.postRepo.findByCategory(categroy);
		List<PostDto> postDtos = findByCategory.stream().map((Post) -> this.modelMapper.map(Post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> findByTitleContaining = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> collect = findByTitleContaining.stream().map((post)->this.modelMapper.map(findByTitleContaining, PostDto.class)).collect(Collectors.toList());
		

		return collect;
	}

	@Override
	public List<PostDto> postByUser(Integer userId) {
		User users = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourcesNotFoundException("User", "userId", userId));
		List<Post> postbyusers = this.postRepo.findByUser(users);
		List<PostDto> userDtos = postbyusers.stream().map((user) -> this.modelMapper.map(user, PostDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

}
