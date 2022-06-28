package com.dionysio.vehiclesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@GetMapping
	@RequestMapping("/Dashboard/Index")
	public String dashboard() {
		return "/user/dashboard";
	}


}
