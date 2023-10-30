package com.tristant.fastTrackFriends.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tristant.fastTrackFriends.models.Post;
import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.services.PostService;
import com.tristant.fastTrackFriends.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private UserService uServ;
	
	@Autowired
	private PostService postServ;
	
	
	@GetMapping("/add")
	public String newPost(@ModelAttribute("post") Post post, Model viewModel, HttpSession session) {
		User user = uServ.findById((Long) session.getAttribute("userId"));
		 List<String> drivers = Arrays.asList("Max Verstappen", "Charles Leclerc", "Lando Norris");

	    viewModel.addAttribute("drivers", drivers);
	    viewModel.addAttribute("post", new Post()); // Initialize an empty Post object
		viewModel.addAttribute("user", user);
		return "newPost.jsp";
	}
	
	@PostMapping("/created")
	public String recordPost(@Valid @ModelAttribute("post") Post post, BindingResult result, HttpSession session) {
	    if (result.hasErrors()) {
	        return "newPost.jsp";
	    }

	    Long userId = (Long) session.getAttribute("userId");
	    User user = uServ.findById(userId);
	    post.setUser(user);

	    // If the selected driver is "other," use the value from otherDriver field
	    if ("other".equals(post.getDriver())) {
	        post.setDriver(post.getOtherDriver());
	    }

	    // Continue saving the post
	    postServ.createPost(post);

	    return "redirect:/home";
	}


	
	@GetMapping("/edit/{postId}")
	public String editPost(@PathVariable("postId") Long postId, Model model, HttpSession session) {
		Post post = postServ.findById(postId);
		User user = uServ.findById((Long)session.getAttribute("userId"));
		if(post != null && post.getUser().getId().equals(user.getId())) {
			model.addAttribute("post", post);
			return "editPost.jsp";
		} else {
			return "redirect:/home";
		}
	}
	
	@PutMapping("/update/{id}")
	public String updatePost(@Valid @ModelAttribute("post") Post post, BindingResult result) {
		if(result.hasErrors()) {
			return "editPost.jsp";
		} else {
			this.postServ.updatePost(post);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/view/{postId}")
	public String viewPost(@PathVariable("postId") Long postId, Model model) {
		Post post = postServ.findById(postId);
		model.addAttribute("post", post);
		return "viewPost.jsp";
	}
	
    @GetMapping("/details")
    public String showPostDetails(@RequestParam Long user_id, @RequestParam Long post_id, Model model) {
        // Retrieve the user and post data from your services
        User user = uServ.findById(user_id);
        Post post = postServ.findById(post_id);

        // Add the user and post data to the model for use in the JSP template
        model.addAttribute("user", user);
        model.addAttribute("post", post);

        // Return the name of your JSP template (e.g., "post-details")
        return "post-details";
    }
	
	@GetMapping("/delete/{postId}")
	public String deletePost(@PathVariable("postId") Long postId) {
		this.postServ.deletePost(postId);
		return "redirect:/home";
	}
	
	@GetMapping("/return/home")
	public String home(Model viewModel, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("currentUser", uServ.findById((Long) session.getAttribute("userId")));
		return "redirect:/home";
	}
	
	
}
