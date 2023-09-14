package com.tristant.fastTrackFriends.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tristant.fastTrackFriends.models.User;

//Declaration of a repository interface for the User entity
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
 // Method to retrieve all users from the database
	List<User> findAll();
	
 // Method to find users whose usernames contain a specific name
	List<User> findByUsernameContaining(String name);
	
 // Method to find a user by their email address (returns an optional)
	Optional<User> findByEmail(String email);
	
 // Method to count the number of users whose usernames contain a specific username
	Long countByUsernameContaining(String username);
	
 // Method to delete users whose usernames contain a specific username and return the number of deleted users
	Long deleteByUsernameContaining(String username);	
}
