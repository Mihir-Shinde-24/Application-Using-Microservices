package com.grt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grt.entities.User;

public interface UserRepo extends JpaRepository<User, String>{

}
