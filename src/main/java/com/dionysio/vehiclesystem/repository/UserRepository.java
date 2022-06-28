package com.dionysio.vehiclesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysio.vehiclesystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailId(String emailId);
}
