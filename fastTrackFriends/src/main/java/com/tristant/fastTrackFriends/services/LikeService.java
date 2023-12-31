package com.tristant.fastTrackFriends.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tristant.fastTrackFriends.models.Like;
import com.tristant.fastTrackFriends.repositories.LikeRepository;
import com.tristant.fastTrackFriends.repositories.PostRepository;
import com.tristant.fastTrackFriends.repositories.UserRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Autowired
    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    // Create a new like
    public Like createLike(Like like) {
        System.out.println("Saving Like: " + like); // Add this log statement
        return likeRepository.save(like);
    }

    // Get a like by its ID
    public Optional<Like> getLikeById(Long likeId) {
        return likeRepository.findById(likeId);
    }

    // Get likes for a specific post
    public List<Like> getLikesForPost(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    // Get likes by a specific user
    public List<Like> getLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    // Update a like (e.g., if you need to modify some properties)
    public Like updateLike(Like like) {
        return likeRepository.save(like);
    }

    // Delete a like by its ID
    public void deleteLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Long addLikeToPost(Long postId) {
        // Assuming you have a Post entity with a postId field
        // Here, you can add a new Like entity to the database for the given postId
        Like newLike = new Like();
        newLike.setId(postId); // Set the postId for the new like
        likeRepository.save(newLike);

        // After adding the like, you can retrieve the updated like count for the post
        Long likeCount = likeRepository.countByPostId(postId);

        return likeCount;
    }
    
    public int getLikeCountForPost(Long postId) {
        List<Like> likesForPost = likeRepository.findByPostId(postId);
        return likesForPost.size(); // Return the count of likes
    }
    
    public List<Like> getAllLikes() {
        return (List<Like>) likeRepository.findAll();
    }
    
    public int likePost(Long postId, Long userId) {
        // Check if the user has already liked the post to prevent duplicate likes
        Like existingLike = likeRepository.findByPostIdAndUserId(postId, userId);

        if (existingLike != null) {
            // User has already liked the post, return the current like count
            return likeRepository.countLikesForPost(postId);
        }

        // Create a new like entry for the post and user
        Like like = new Like();
        like.setPost(postRepository.findById(postId).orElse(null));
        like.setUser(userRepository.findById(userId).orElse(null));
        likeRepository.save(like);

        // Return the updated like count for the post
        return likeRepository.countLikesForPost(postId);
    }

}