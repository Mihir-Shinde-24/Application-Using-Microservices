package com.grt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grt.entities.User;
import com.grt.services.Services;


@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	Services service;
	
	// 1. Get All
	@GetMapping()
	public ResponseEntity<List<User>> getUsers()
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
	}
	
	
	// 2. Get Single
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getUser(id));
	}

	// 3. Add
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User newUser)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(newUser));
	}

	// 4. Update
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User newUser)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(newUser));
	}

	// 5. Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));		
	}

}
