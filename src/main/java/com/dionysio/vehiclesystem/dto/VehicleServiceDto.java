package com.dionysio.vehiclesystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.dionysio.vehiclesystem.model.VehicleService;

public class VehicleServiceDto {

	@NotBlank
	private String vehicleRegNumber;

	@NotNull
	private int odometerReading;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate serviceDoneDate;

	@NotBlank
	private String serviceDetails;

	@NotBlank
	private String serviceDealerDetails;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate nextServiceDueDate;

	private LocalDateTime createdDate;

	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(String vehicleRegNumber) {
		System.out.println("Setando Reg Number" + vehicleRegNumber);
		this.vehicleRegNumber = vehicleRegNumber;
	}

	public int getOdometerReading() {
		return odometerReading;
	}

	public void setOdometerReading(int odometerReading) {
		this.odometerReading = odometerReading;
	}

	public LocalDate getServiceDoneDate() {
		return serviceDoneDate;
	}

	public void setServiceDoneDate(LocalDate serviceDoneDate) {
		this.serviceDoneDate = serviceDoneDate;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public String getServiceDealerDetails() {
		return serviceDealerDetails;
	}

	public void setServiceDealerDetails(String serviceDealerDetails) {
		this.serviceDealerDetails = serviceDealerDetails;
	}

	public LocalDate getNextServiceDueDate() {
		return nextServiceDueDate;
	}

	public void setNextServiceDueDate(LocalDate nextServiceDueDate) {
		this.nextServiceDueDate = nextServiceDueDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public VehicleService toVehicleService() {

		VehicleService vehicleService = new VehicleService();

		vehicleService.setOdometerReading(odometerReading);
		vehicleService.setServiceDoneDate(serviceDoneDate.atStartOfDay());
		vehicleService.setServiceDetails(serviceDetails);
		vehicleService.setServiceDealerDetails(serviceDealerDetails);
		vehicleService.setNextServiceDueDate(nextServiceDueDate.atStartOfDay());
		vehicleService.setCreatedDate(LocalDateTime.now());

		return vehicleService;
	}

}
