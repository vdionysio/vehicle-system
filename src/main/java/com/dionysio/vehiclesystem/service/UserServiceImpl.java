package com.dionysio.vehiclesystem.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dionysio.vehiclesystem.dto.UserRegistrationDto;
import com.dionysio.vehiclesystem.model.Role;
import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setContactNumber(registrationDto.getContactNumber());
		user.setCreatedDate(LocalDateTime.now());
		user.setEmailId(registrationDto.getEmailId());
		user.setPasscode(passwordEncoder.encode(registrationDto.getPasscode()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailId(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid attempt, Please try again.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPasscode(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
