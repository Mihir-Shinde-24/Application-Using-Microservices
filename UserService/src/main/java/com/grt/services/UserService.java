package com.grt.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grt.entities.Hotel;
import com.grt.entities.Rating;
import com.grt.entities.User;
import com.grt.exceptions.ResourceNotFoundException;
import com.grt.external.services.HotelService;
import com.grt.repositories.UserRepo;

@Service
public class UserService implements Services {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public User getUser(String id) {
		User user = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with given ID is Not Found on Server."));
		
		Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getU_id(), Rating[].class);
		
		List<Rating> ratingsList =  Arrays.stream(ratings).toList().stream().map((rating)->{
			
//			Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		
		logger.info("{}",ratingsList);
		
			
		
		user.setU_ratings(ratingsList);
		
		return user;
		
	}

	@Override
	public User addUser(User newUser) {
		
		String randomUuid = UUID.randomUUID().toString();
		newUser.setU_id(randomUuid);
		newUser.setU_ratings(new ArrayList<>());
		System.out.println(newUser);
		
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
