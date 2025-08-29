package com.travelmanagementservice.tmss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagementservice.tmss.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByUsername(String username);
}
