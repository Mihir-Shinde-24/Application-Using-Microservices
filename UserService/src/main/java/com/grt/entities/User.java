package com.grt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

	@Id
	@Column
	private String u_id;
	
	@Column
	private String u_firstName;
	
	@Column
	private String u_lastName;
	
	@Column
	private String u_email;
	
	@Transient
	private List<Rating> u_ratings = new ArrayList<>();
}
