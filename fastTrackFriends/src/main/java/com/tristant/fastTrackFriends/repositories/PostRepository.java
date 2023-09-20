package com.tristant.fastTrackFriends.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tristant.fastTrackFriends.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	
	List<Post> findAll();
	
	List<Post> findByNameContaining(String driver);
	
	Optional<Post> findByName(String driver);
	
	Long countByNameContaining(String driver);
	
	Long deleteByNameContaining(String driver);
}
