package com.grt.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grt.entities.User;
import com.grt.exceptions.ResourceNotFoundException;
import com.grt.repositories.UserRepo;

@Service
public class UserService implements Services {

	@Autowired
	private UserRepo repo;

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public User getUser(String id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given ID is Not Found on Server."));
	}

	@Override
	public User addUser(User newUser) {
		
		String randomUuid = UUID.randomUUID().toString();
		newUser.setU_id(randomUuid);
		return repo.save(newUser);
	}

	@Override
	public User updateUser(User newUser) {
			return repo.save(newUser);
	}

	@Override
	public String deleteUser(String id) {
		Optional<User> userOptional = repo.findById(id);

		if (userOptional.isPresent()) 
		{
			repo.deleteById(id);
			return "User deleted successfully";
		} 
		throw new ResourceNotFoundException("User doesn't exist");
	}

}
