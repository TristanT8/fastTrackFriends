package com.tristant.fastTrackFriends.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.services.UserService;
import com.tristant.fastTrackFriends.validators.LoginValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	@GetMapping("/")
	public String loginRegister(Model loginModel) {
		loginModel.addAttribute("newUser", new User());
		loginModel.addAttribute("newLoginValidator", new LoginValidator());
		return "welcome.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model viewModel, HttpSession session) {
		User registerUser = this.userServ.registerUser(newUser, result);
		if (result.hasErrors()) {
			viewModel.addAttribute("errors", result.getAllErrors());
			viewModel.addAttribute("newLoginValidator", new LoginValidator());
			return "welcome.jsp";
		}
		session.setAttribute("userId", registerUser.getId());
		return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute ("newLoginValidator") LoginValidator loginUser, BindingResult result,
			Model loginModel, HttpSession session) {
		User user = userServ.loginUser(loginUser, result);
		System.out.println("User: " + user);
		
		if (result.hasErrors()) {
			loginModel.addAttribute("errors", result.getAllErrors());
			loginModel.addAttribute("newUser", new User());
			return "welcome.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home (Model homeModel, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		System.out.println("User IF: " + userId);
		
		if (userId != null) {
			User currentUser = this.userServ.findById(userId);
			homeModel.addAttribute("currentUser", currentUser);
			
			
		}
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}	
}














