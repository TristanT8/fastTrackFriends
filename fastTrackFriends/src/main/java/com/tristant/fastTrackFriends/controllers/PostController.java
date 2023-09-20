package com.tristant.fastTrackFriends.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tristant.fastTrackFriends.models.Post;
import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private UserService uServ;
	
	
	@GetMapping("/add")
	public String newPost(@ModelAttribute("post") Post post, Model viewModel, HttpSession session) {
		User user = uServ.findById((Long) session.getAttribute("userId"));
		viewModel.addAttribute("user", user);
		return "newPost.jsp";
	}
	
	
}
