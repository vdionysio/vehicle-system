package com.dionysio.vehiclesystem.model;

import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String vehicleRegNumber;
	
	private String chassisNumber;
	
	private String engineNumber;
	
	private String make;
	
	private int makeMonth;
	
	private int makeYear;
	
	private LocalDateTime purchaseDate;
	
	private int currentOdometerReading;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", fetch = FetchType.LAZY)
	private List<VehicleService> vehicleServices;
	
	public List<VehicleService> getVehicleServices() {
		return vehicleServices;
	}
	
	public void setVehicleServices(List<VehicleService> vehicleServices) {
		this.vehicleServices = vehicleServices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getMakeMonth() {
		return makeMonth;
	}

	public void setMakeMonth(int makeMonth) {
		this.makeMonth = makeMonth;
	}

	public int getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(int makeYear) {
		this.makeYear = makeYear;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getCurrentOdometerReading() {
		return currentOdometerReading;
	}

	public void setCurrentOdometerReading(int currentOdometerReading) {
		this.currentOdometerReading = currentOdometerReading;
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
