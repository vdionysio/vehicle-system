package com.dionysio.vehiclesystem.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dionysio.vehiclesystem.dto.UserRegistrationDto;
import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.repository.UserRepository;

@Controller
public class UserController {

	private static final String EDIT_FORM = "/user/edit";
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	@RequestMapping("/UserDetail/Index")
	public String userDetail(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmailId(username);

		model.addAttribute("user", user);
		return "/user/detail";
	}

	@GetMapping("/UserDetail/Edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model, UserRegistrationDto userRegistrationDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmailId(username);

		userRegistrationDto.setEmailId(user.getEmailId());
		userRegistrationDto.setContactNumber(user.getContactNumber());
		userRegistrationDto.setCreatedDate(user.getCreatedDate());
		userRegistrationDto.setPasscode("DefaultPass");
		userRegistrationDto.setFirstName(user.getFirstName());
		userRegistrationDto.setLastName(user.getLastName());

		model.addAttribute("id", id);
		return EDIT_FORM;
	}

	@PostMapping("/UserDetail/Edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model, @Valid UserRegistrationDto userRegistrationDto,
			BindingResult result) {
		if (result.hasErrors()) {
			return EDIT_FORM;
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmailId(username);

		if (id != user.getId()) {
			model.addAttribute("errorMessage", "Not authorized");
			return EDIT_FORM;
		}

		String newEmailId = userRegistrationDto.getEmailId();
		User userAlreadyExists = userRepository.findByEmailId(newEmailId);

		if (!newEmailId.equals(username) && userAlreadyExists != null) {
			model.addAttribute("errorMessage", "Email Id Already Exists.");
			return EDIT_FORM;
		}
		
		userRegistrationDto.updateUser(user);
		user.setUpdatedDate(LocalDateTime.now());
		userRepository.save(user);

		if (!newEmailId.equals(username)) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
			updatedAuthorities.add(new SimpleGrantedAuthority("USER"));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPasscode(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}

		return "redirect:/UserDetail/Index";
	}
}
