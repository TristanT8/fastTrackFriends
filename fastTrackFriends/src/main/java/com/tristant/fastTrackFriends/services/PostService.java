package com.tristant.fastTrackFriends.services;

import java.util.List;

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
}
