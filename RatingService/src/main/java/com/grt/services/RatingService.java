package com.grt.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grt.entities.Rating;
import com.grt.exceptions.ResourceNotFoundException;
import com.grt.repositories.RatingRepo;

@Service
public class RatingService implements Services{

	@Autowired
	RatingRepo repo;
	
	@Override
	public Rating addRating(Rating newRating) {
		String randomUUID = UUID.randomUUID().toString();
		newRating.setRatingId(randomUUID);
		return repo.save(newRating);
	}

	@Override
	public List<Rating> getRatings() {
		return repo.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String u_id) {
		
		return repo.findAllByUserId(u_id).orElseThrow(()-> new ResourceNotFoundException("No Ratings Found for this user id."));
		
	}

	@Override
	public List<Rating> getRatingsByHotelId(String h_id) {

		return repo.findAllByHotelId(h_id).orElseThrow(()-> new ResourceNotFoundException("No Ratings Found for this hotel id."));
	}

	@Override
	public String deleteRating(String r_id) {
		Optional<Rating> rating = repo.findById(r_id);
		
		if(rating.isPresent())
		{
			repo.deleteById(r_id);
			return "Rating Deleted Succesfully.";			
		}
		throw new ResourceNotFoundException("No Ratings Found for this rating id.");
	}

} 
