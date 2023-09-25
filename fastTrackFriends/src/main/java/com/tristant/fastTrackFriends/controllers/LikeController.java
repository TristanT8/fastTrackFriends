package com.tristant.fastTrackFriends.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tristant.fastTrackFriends.models.Like;
import com.tristant.fastTrackFriends.models.Post;
import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.services.LikeService;
import com.tristant.fastTrackFriends.services.PostService;
import com.tristant.fastTrackFriends.services.UserService;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    
    private final PostService postService;
    
    private final UserService userService;


    @Autowired
    public LikeController(LikeService likeService, PostService postService, UserService userService) {
        this.likeService = likeService;
		this.postService = postService;
		this.userService = userService;
    }

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/{id}")
    public Optional<Like> getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id);
    }

    @PostMapping
    public Like createLike(@RequestBody Like like) {
        return likeService.createLike(like);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLikeById(id);
    }
    
 // Your controller method
    @PostMapping("/likePost")
    public ResponseEntity<Map<String, Object>> likePost(@RequestBody Map<String, Long> requestBody) {
        Long postId = requestBody.get("postId");
        Long userId = requestBody.get("userId");
        System.out.println("Received postId: " + postId);
        System.out.println("Received userId: " + userId);

        Post post = postService.findById(postId);
        User user = userService.findById(userId);

        if (post == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);

        Like savedLike = likeService.createLike(like);

        // Get the updated like count for the post
        int updatedLikeCount = likeService.getLikeCountForPost(postId);

        // Create a response object containing the saved like and like count
        Map<String, Object> response = new HashMap<>();
        response.put("like", savedLike);
        response.put("likeCount", updatedLikeCount);
        System.out.println("Saved Like: " + savedLike);
        System.out.println("Updated Like Count: " + updatedLikeCount);

        return ResponseEntity.ok(response);
    }




}
