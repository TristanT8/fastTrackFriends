package com.tristant.fastTrackFriends.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tristant.fastTrackFriends.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	
	List<Post> findAll();
	
	List<Post> findByDriverContaining(String driver);
	
	Optional<Post> findByDriver(String driver);
	
	Long countByDriverContaining(String driver);
	
	Long deleteByDriverContaining(String driver);
}
