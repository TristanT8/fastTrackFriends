package com.tristant.fastTrackFriends.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tristant.fastTrackFriends.models.Like;
import com.tristant.fastTrackFriends.services.LikeService;
import com.tristant.fastTrackFriends.services.PostService;
import com.tristant.fastTrackFriends.services.UserService;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:8080") // Allow requests from your STS domain
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
    @PostMapping("/like/{postId}/")
    @ResponseBody
    public Map<String, Integer> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        int likeCount = likeService.likePost(postId, userId);
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return response;
    }
}
