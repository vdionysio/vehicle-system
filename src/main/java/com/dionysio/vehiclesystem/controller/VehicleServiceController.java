package com.dionysio.vehiclesystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dionysio.vehiclesystem.dto.VehicleServiceDto;
import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.model.Vehicle;
import com.dionysio.vehiclesystem.model.VehicleService;
import com.dionysio.vehiclesystem.repository.UserRepository;
import com.dionysio.vehiclesystem.repository.VehicleRepository;
import com.dionysio.vehiclesystem.repository.VehicleServiceRepository;

@Controller
public class VehicleServiceController {

	@Autowired
	private VehicleServiceRepository vehicleServiceRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/VehicleServiceHistory/Index")
	public String services(Model model) {
		User user = getUserFromContext();

		List<VehicleService> services = vehicleServiceRepository.findVehicleServiceByVehicleUser(user);

		model.addAttribute("services", services);

		return "/vehicleService/detail";
	}

	@GetMapping("/VehicleServiceHistory/Create")
	public String registerPage(VehicleServiceDto vehicleServiceDto, Model model) {
		List<String> regNumbers = getRegNumbers();

		model.addAttribute("regNumbers", regNumbers);
		return "/vehicleService/register";
	}

	@PostMapping("/VehicleServiceHistory/Create")
	public String register(@Valid VehicleServiceDto vehicleServiceDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> regNumbers = getRegNumbers();

			model.addAttribute("regNumbers", regNumbers);
			return "/vehicleService/register";
		}
		Vehicle vehicle = vehicleRepository.findByVehicleRegNumber(vehicleServiceDto.getVehicleRegNumber());

		if (vehicle.getPurchaseDate().isAfter(vehicleServiceDto.getServiceDoneDate().atStartOfDay())) {
			List<String> regNumbers = getRegNumbers();

			model.addAttribute("regNumbers", regNumbers);
			model.addAttribute("errorMessage", "Service Done Date Should be greater than Purchase Date");
		}
		
		VehicleService vehicleService = vehicleServiceDto.toVehicleService();
		vehicleService.setVehicle(vehicle);

		vehicleServiceRepository.save(vehicleService);

		return "redirect:/VehicleServiceHistory/Index";
	}

	private List<String> getRegNumbers() {
		User user = getUserFromContext();
		return vehicleRepository.findByUser(user).stream().map(v -> v.getVehicleRegNumber())
				.collect(Collectors.toList());
	}

	private User getUserFromContext() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmailId(username);
	}
}
