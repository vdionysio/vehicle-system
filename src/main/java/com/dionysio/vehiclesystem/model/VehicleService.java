package com.dionysio.vehiclesystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_services")
public class VehicleService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	private int odometerReading;
	
	private LocalDateTime serviceDoneDate;
	
	private String serviceDetails;
	
	private String serviceDealerDetails;
	
	private LocalDateTime nextServiceDueDate;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getOdometerReading() {
		return odometerReading;
	}

	public void setOdometerReading(int odometerReading) {
		this.odometerReading = odometerReading;
	}

	public LocalDateTime getServiceDoneDate() {
		return serviceDoneDate;
	}

	public void setServiceDoneDate(LocalDateTime serviceDoneDate) {
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

	public LocalDateTime getNextServiceDueDate() {
		return nextServiceDueDate;
	}

	public void setNextServiceDueDate(LocalDateTime nextServiceDueDate) {
		this.nextServiceDueDate = nextServiceDueDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
