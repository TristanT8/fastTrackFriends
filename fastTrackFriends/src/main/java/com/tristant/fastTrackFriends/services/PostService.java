package com.tristant.fastTrackFriends.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tristant.fastTrackFriends.models.Post;
import com.tristant.fastTrackFriends.repositories.PostRepository;

@Service
public class PostService {
	
	private final PostRepository postRepo;
	
	public PostService(PostRepository postRepository ) {
		this.postRepo = postRepository;
	}
	
	public List<Post> getAllPosts() {
		return postRepo.findAll();
	}
	
	public Post findById(Long id) {
		return this.postRepo.findById(id).orElse(null);
	}
	
	public Post createPost(Post post) {
		return this.postRepo.save(post);
	}
	
	public Post readPost(Long id) {
		Optional<Post> optionalPost = postRepo.findById(id);
		if(optionalPost.isPresent()) {
			return optionalPost.get();
		} else {
			return null;
		}
	}
	
	public Post updatePost(Post post) {
		Post oldPost = this.findById(post.getId());
		post.setUser(oldPost.getUser());
		return this.postRepo.save(post);
	}
	
	public void deletePost(Long id) {
		Optional<Post> optionalPost = postRepo.findById(id);
		if(optionalPost.isPresent()) {
			Post unposted = optionalPost.get();
			postRepo.delete(unposted);
		}
	}

}
