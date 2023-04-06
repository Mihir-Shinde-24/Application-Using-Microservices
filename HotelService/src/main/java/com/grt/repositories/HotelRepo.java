package com.grt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grt.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String>{

}
