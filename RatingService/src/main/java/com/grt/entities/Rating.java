package com.grt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Rating {

	@Id
	private String ratingId;
	@Column
	private String userId;
	@Column
	private String hotelId;
	@Column
	private int rating;
	@Column
	private String feedback;
	
}
