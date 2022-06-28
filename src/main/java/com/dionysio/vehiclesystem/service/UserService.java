package com.dionysio.vehiclesystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dionysio.vehiclesystem.dto.UserRegistrationDto;
import com.dionysio.vehiclesystem.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
}