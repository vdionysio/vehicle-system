package com.dionysio.vehiclesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping
	@RequestMapping(value = "/login")
	public String login() {
		return "/user/login";
	}
}
