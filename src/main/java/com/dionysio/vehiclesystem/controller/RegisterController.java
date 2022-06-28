package com.dionysio.vehiclesystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dionysio.vehiclesystem.dto.UserRegistrationDto;
import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.repository.UserRepository;

@Controller
@RequestMapping("/Registration/Create")
public class RegisterController {

	private static final String REGISTER_FORM = "/user/register";
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String registerPage(UserRegistrationDto userRegistrationDto) {
		return REGISTER_FORM;
	}

	@PostMapping
	public String registerUser(@Valid UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return REGISTER_FORM;
		}

		User user = userRegistrationDto.toUser();
		User userAlreadyExists = userRepository.findByEmailId(user.getEmailId());
		if (userAlreadyExists != null) {
			model.addAttribute("errorMessage", "Email Id Already Exists.");
			return REGISTER_FORM;
		}
		userRepository.save(user);

		return "redirect:/login";
	}
}
