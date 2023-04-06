package com.grt.services;

import java.util.List;

import com.grt.entities.Hotel;

public interface Services {

	//1. Get ALL
	public List<Hotel> getHotels();
	
	//2. Get Single
	public Hotel getHotel(String id);
	
	//3. Add 
	public Hotel addHotel(Hotel newHotel);
	
	//4. Update
	public Hotel updateHotel(Hotel newHotel);
	
	//5. Delete
	public String deleteHotel(String id);
}
