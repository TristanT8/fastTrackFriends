package com.tristant.fastTrackFriends.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tristant.fastTrackFriends.models.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    
    // Find likes for a specific post
    List<Like> findByPostId(Long postId);
    
    // Find likes by the user who made the like
    List<Like> findByUserId(Long userId);

	Long countByPostId(Long postId);
	
    

}
