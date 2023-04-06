package com.grt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grt.entities.Rating;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating, String> {

	//1. Get Ratings by user id
	Optional<List<Rating>> findAllByUserId(String u_id);

	//2. Get Ratings by hotel id
	Optional<List<Rating>> findAllByHotelId(String h_id);
}
