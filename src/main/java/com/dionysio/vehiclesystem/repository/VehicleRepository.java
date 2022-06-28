package com.dionysio.vehiclesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionysio.vehiclesystem.model.User;
import com.dionysio.vehiclesystem.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findByUser(User user);

	Vehicle findByVehicleRegNumber(String vehicleRegNumber);
}
