package com.grt.services;

import java.util.List;

import com.grt.entities.Rating;

public interface Services {

	// 1. Create Rating
	Rating addRating(Rating newRating);

	// 2. Get All Rating
	List<Rating> getRatings();

	// 3. Get All Rating by user id
	List<Rating> getRatingsByUserId(String u_id);

	// 4. Get All Rating by hotel id
	List<Rating> getRatingsByHotelId(String h_id);
	
	//5. Delete Rating
	String deleteRating(String r_id);
}
