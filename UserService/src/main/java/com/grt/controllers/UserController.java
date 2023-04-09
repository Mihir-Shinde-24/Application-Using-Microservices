package com.grt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	Services service;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// 1. Get All
	@GetMapping()
	public ResponseEntity<List<User>> getUsers()
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
	}
	
	
	// 2. Get Single
	@GetMapping("/{id}")
	@CircuitBreaker(name = "getUserBreaker", fallbackMethod = "getUserFallback")
	public ResponseEntity<User> getUser(@PathVariable("id") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getUser(id));
	}
	
	public ResponseEntity<User> getUserFallback(String id, Exception ex)
	{
		logger.info("Fallback Method executed ",ex.getMessage());
		User user = User.builder()
							.u_id("0")
							.u_firstName("DUMMY")
							.u_lastName("USER")
							.u_email("dummy@email.com")
							.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
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
