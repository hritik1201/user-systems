package com.usersystem.UserSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usersystem.UserSystem.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {
	

}
