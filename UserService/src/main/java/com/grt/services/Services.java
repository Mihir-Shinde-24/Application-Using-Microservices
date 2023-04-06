package com.grt.services;

import java.util.List;

import com.grt.entities.User;

public interface Services {

	//1. Get ALL
	public List<User> getUsers();
	
	//2. Get Single
	public User getUser(String id);
	
	//3. Add 
	public User addUser(User newUser);
	
	//4. Update
	public User updateUser(User newUser);
	
	//5. Delete
	public String deleteUser(String id);
}
