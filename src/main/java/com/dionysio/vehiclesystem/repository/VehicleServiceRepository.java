package com.dionysio.vehiclesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.model.VehicleService;

public interface VehicleServiceRepository extends JpaRepository<VehicleService, Long> {
	List<VehicleService> findVehicleServiceByVehicleUser(User user);
}
