package com.tristant.fastTrackFriends.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tristant.fastTrackFriends.models.User;
import com.tristant.fastTrackFriends.repositories.UserRepository;
import com.tristant.fastTrackFriends.validators.LoginValidator;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	// Method to register a new user
		public User registerUser(User newUser, BindingResult result) {
			// Check if a user with the same email already exists
			Optional<User> registerNewUser = userRepo.findByEmail(newUser.getEmail());
			if (registerNewUser.isPresent()) {
				result.rejectValue("email", "Matches", "Email has already been taken");
			}
			// Check if the password and confirm password match
			if (!newUser.getPassword().equals(newUser.getConfirm())) {
				result.rejectValue("confirmPassword", "Matches", "Passwords must match");
			}
			// If there are validation errors, return null
			if (result.hasErrors()) {
				return null;
			}
			// Hash the user's password using BCrypt and save the user
			String bcryptPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(bcryptPassword);
			return this.userRepo.save(newUser);
		}
		
		// Method to log in a user
		public User loginUser(LoginValidator newLogin, BindingResult result) {
			// Find the user by email
			Optional<User> loginUser = userRepo.findByEmail(newLogin.getEmail());
			if (loginUser.isPresent()) {
				User verifyUser = loginUser.get();
				// Check if the provided password matches the hashed password
				if (!BCrypt.checkpw(newLogin.getPassword(), verifyUser.getPassword())) {
					result.rejectValue("password", "Matches", "Invalid Credentials");
				}
				return verifyUser;
			} else {
				// If the email is not registered, reject and return null
				result.rejectValue("email", "Matches", "Email is unregistered, please register this Email to continue");
				return null;
			}
		}
		
		// Method to retrieve a list of all users
		public List<User> allUsers(){
			return userRepo.findAll();
		}
		
		// Method to find a user by their ID
		public User findById(Long id) {
			return this.userRepo.findById(id).orElse(null);
		}
		
		// Method to find a user by their email
		public User findByEmail(String email) {
			return this.userRepo.findByEmail(email).orElse(null);
		}


	}