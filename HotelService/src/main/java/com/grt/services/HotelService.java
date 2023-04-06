package com.grt.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grt.entities.Hotel;
import com.grt.exceptions.ResourceNotFoundException;
import com.grt.repositories.HotelRepo;

@Service
public class HotelService implements Services{

	@Autowired
	private HotelRepo repo;

	@Override
	public List<Hotel> getHotels() {
		return repo.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with given ID is Not Found on Server."));
	}

	@Override
	public Hotel addHotel(Hotel newHotel) {
		
		String randomUuid = UUID.randomUUID().toString();
		newHotel.setH_id(randomUuid);
		return repo.save(newHotel);
	}

	@Override
	public Hotel updateHotel(Hotel newHotel) {
			return repo.save(newHotel);
	}

	@Override
	public String deleteHotel(String id) {
		Optional<Hotel> HotelOptional = repo.findById(id);

		if (HotelOptional.isPresent()) 
		{
			repo.deleteById(id);
			return "Hotel deleted successfully";
		} 
		throw new ResourceNotFoundException("Hotel doesn't exist");
	}
}
