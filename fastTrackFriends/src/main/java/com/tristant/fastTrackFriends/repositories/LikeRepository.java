package com.tristant.fastTrackFriends.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tristant.fastTrackFriends.models.Like;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    
    // Find likes for a specific post
    List<Like> findByPostId(Long postId);
    
    // Find likes by the user who made the like
    List<Like> findByUserId(Long userId);

    // Define a query method to count likes for a specific post
    Long countByPostId(Long postId);

    Like findByPostIdAndUserId(Long postId, Long userId);

    // Custom query to count likes for a specific post
    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId")
    int countLikesForPost(Long postId);
}
