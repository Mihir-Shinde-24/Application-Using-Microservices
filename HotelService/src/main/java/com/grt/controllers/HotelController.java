package com.grt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grt.entities.Hotel;
import com.grt.services.Services;


@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	Services service;
	
	// 1. Get All
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels()
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getHotels());
	}
	
	
	// 2. Get Single
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable("id") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getHotel(id));
	}

	// 3. Add
	@PostMapping
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel newHotel)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addHotel(newHotel));
	}

	// 4. Update
	@PutMapping
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel newHotel)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.updateHotel(newHotel));
	}

	// 5. Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable("id") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteHotel(id));		
	}

}
