package com.tristant.fastTrackFriends.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User registerUser(User newUser, BindingResult result) {
		Optional <User> registerNewUser = userRepo.findByEmail(newUser.getEmail());
		if(registerNewUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email has already been taken");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Matches", "Paswords must match");
		}
		if(result.hasErrors()) {
			return null;
		}
		
	}
}
