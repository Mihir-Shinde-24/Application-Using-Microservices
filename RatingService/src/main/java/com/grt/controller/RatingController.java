package com.grt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grt.entities.Rating;
import com.grt.payload.ApiResponse;
import com.grt.services.Services;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	Services service;
	
	// 1. Create Rating
	@PostMapping
	public ResponseEntity<Rating> addRating(@RequestBody Rating newRating)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addRating(newRating));
	}

	// 2. Get All Rating
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings()
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatings());
	}

	// 3. Get All Rating by user id
	@GetMapping("/users/{u_id}")
	public ResponseEntity<List<Rating>>  getRatingsByUserId(@PathVariable("u_id") String u_id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatingsByUserId(u_id));
	}

	// 4. Get All Rating by hotel id
	@GetMapping("/hotels/{h_id}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable("h_id") String h_id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getRatingsByHotelId(null));
	}

	//5. Delete rating
	@DeleteMapping("/delete/{r_id}")
	public ResponseEntity<ApiResponse> deleteRating(@PathVariable("r_id") String r_id)
	{
		ApiResponse response = ApiResponse.builder()
											.message(service.deleteRating(r_id))
											.status(HttpStatus.OK)
											.success(true)
											.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
