package com.dionysio.vehiclesystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.dionysio.vehiclesystem.model.Vehicle;

public class VehicleRegistrationDto {

	private static final String ALPHANUM_MSG = "Only Alphabets and Numbers Allowed";

	private static final String ALPHA_NUM = "^[a-zA-Z0-9]+$";

	@Pattern(regexp = "[A-Z][A-Z][0-9][0-9][A-Z][A-Z][0-9][0-9][0-9][0-9]")
	private String registrationNumber;

	@Pattern(regexp = ALPHA_NUM, message = ALPHANUM_MSG)
	private String chassisNumber;

	@Pattern(regexp = ALPHA_NUM, message = ALPHANUM_MSG)
	private String engineNumber;

	@Pattern(regexp = ALPHA_NUM, message = ALPHANUM_MSG)
	private String make;

	@Pattern(regexp = "^[1-9]|10|11|12$")
	private String makeMonth;

	@Pattern(regexp = "[1-2][0-9][0-9][0-9]")
	private String makeYear;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull
	private LocalDate purchaseDate;

	@NotNull
	private int currentOdometerReading;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate createdDate;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	public String getMakeMonth() {
		return makeMonth;
	}

	public void setMakeMonth(String makeMonth) {
		this.makeMonth = makeMonth;
	}

	public String getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getCurrentOdometerReading() {
		return currentOdometerReading;
	}

	public void setCurrentOdometerReading(int currentOdometerReading) {
		this.currentOdometerReading = currentOdometerReading;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean purchaseDateIsValid() {
		System.out.println("ENTROU NO PURCHASE IS VALID");
		LocalDate makeDate = LocalDate.of(Integer.valueOf(makeYear), Integer.valueOf(makeMonth), 1);
		System.out.println("SALVOU O MAKE DATE " + makeDate);

		return this.purchaseDate.isAfter(makeDate);
	}
	
	public Vehicle toVehicle() {
		Vehicle vehicle = new Vehicle();
		
		vehicle.setVehicleRegNumber(registrationNumber);
		vehicle.setChassisNumber(chassisNumber);
		vehicle.setEngineNumber(engineNumber);
		vehicle.setMake(make);
		vehicle.setMakeMonth(Integer.valueOf(makeMonth));
		vehicle.setMakeYear(Integer.valueOf(makeYear));
		vehicle.setPurchaseDate(purchaseDate.atStartOfDay());
		vehicle.setCurrentOdometerReading(currentOdometerReading);
		vehicle.setCreatedDate(LocalDateTime.now());
		
		return vehicle;
	}
	
	public void loadByVehicle(Vehicle vehicle) {
		setRegistrationNumber(vehicle.getVehicleRegNumber());
		setChassisNumber(vehicle.getChassisNumber());
		setEngineNumber(vehicle.getEngineNumber());
		setMake(vehicle.getMake());
		setMakeMonth(String.valueOf(vehicle.getMakeMonth()));
		setMakeYear(String.valueOf(vehicle.getMakeYear()));
		setPurchaseDate(vehicle.getPurchaseDate().toLocalDate());
		setCurrentOdometerReading(vehicle.getCurrentOdometerReading());
		setCreatedDate(vehicle.getCreatedDate().toLocalDate());
	}
	
	public void updateVehicle(Vehicle vehicle) {
		vehicle.setVehicleRegNumber(registrationNumber);
		vehicle.setChassisNumber(chassisNumber);
		vehicle.setEngineNumber(engineNumber);
		vehicle.setMake(make);
		vehicle.setMakeMonth(Integer.valueOf(makeMonth));
		vehicle.setMakeYear(Integer.valueOf(makeYear));
		vehicle.setPurchaseDate(purchaseDate.atStartOfDay());
	}
}
