package com.grt.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grt.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/users/{u_id}")
	public List<Rating> getRatingsById(@PathVariable("u_id") String userId);
}
