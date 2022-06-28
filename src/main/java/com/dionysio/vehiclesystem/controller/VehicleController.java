package com.dionysio.vehiclesystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dionysio.vehiclesystem.dto.VehicleRegistrationDto;
import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.model.Vehicle;
import com.dionysio.vehiclesystem.repository.UserRepository;
import com.dionysio.vehiclesystem.repository.VehicleRepository;

@Controller
public class VehicleController {
	
	private static final String EDIT_FORM = "/vehicle/edit";

	private static final String REDIRECT_VEHICLES_DETAIL = "redirect:/VehicleDetail/Index";

	private static final String REGISTER_FORM = "/vehicle/register";

	private static final String CONTACT_NUMBER = "contactNumber";

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/VehicleDetail/Index")
	public String vehiclesDetail(Model model) {
		User user = getUserFromContext();

		List<Vehicle> vehicles = vehicleRepository.findByUser(user);
		model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
		model.addAttribute("vehicles", vehicles);
		
		return "/vehicle/detail";
	}


	@GetMapping("/VehicleDetail/Create")
	public String registerPage(VehicleRegistrationDto vehicleRegistrationDto, Model model) {
		User user = getUserFromContext();
		
		model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
		return REGISTER_FORM;
	}
	
	@PostMapping("/VehicleDetail/Create")
	public String create(@Valid VehicleRegistrationDto vehicleRegistrationDto, BindingResult result, Model model) {
		User user = getUserFromContext();

		if(result.hasErrors()) {
			model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
			return REGISTER_FORM;
		}
		
		if(!vehicleRegistrationDto.purchaseDateIsValid()) {
			model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
			model.addAttribute("errorMessage", "Purchase Date Should be Greater than Make Month and Year");
			return REGISTER_FORM;
		}
		
		
		Vehicle vehicle = vehicleRegistrationDto.toVehicle();
		vehicle.setUser(user);
		vehicle.setCreatedDate(LocalDateTime.now());
		
		vehicleRepository.save(vehicle);
		
		return REDIRECT_VEHICLES_DETAIL;
	}
	
	@GetMapping("/VehicleDetail/Edit/{id}")
	public String editPage(@PathVariable("id") Long id, VehicleRegistrationDto vehicleRegistrationDto, Model model) {
		User user = getUserFromContext();
		Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
	
		if(vehicle == null || user.getId() != vehicle.getUser().getId()) {
			return REDIRECT_VEHICLES_DETAIL;
		}
		
		vehicleRegistrationDto.loadByVehicle(vehicle);
		model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
		model.addAttribute("id", user.getId());

		return EDIT_FORM;
	}
	
	@PostMapping("/VehicleDetail/Edit/{id}")
	public String edit(@PathVariable("id") Long id, @Valid VehicleRegistrationDto vehicleRegistrationDto, BindingResult result, Model model) {
		User user = getUserFromContext();

		if(result.hasErrors()) {
			model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
			return EDIT_FORM;
		}
		
		if(!vehicleRegistrationDto.purchaseDateIsValid()) {
			model.addAttribute(CONTACT_NUMBER, user.getContactNumber());
			model.addAttribute("errorMessage", "Purchase Date Should be Greater than Make Month and Year");
			return EDIT_FORM;
		}
		
		Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
		
		if (vehicle == null) {
			return REDIRECT_VEHICLES_DETAIL;
		}
		
		vehicleRegistrationDto.updateVehicle(vehicle);
		vehicle.setUpdatedDate(LocalDateTime.now());
		
		vehicleRepository.save(vehicle);
		
		return REDIRECT_VEHICLES_DETAIL;
	}
	
	private User getUserFromContext() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmailId(username);
	}
}
