package com.dionysio.vehiclesystem.dto;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dionysio.vehiclesystem.model.Role;
import com.dionysio.vehiclesystem.model.User;

public class UserRegistrationDto {

	@NotBlank
	@Length(max = 50)
	private String firstName;

	@NotBlank
	@Length(max = 50)
	private String lastName;

	@NotBlank
	@Email
	@Length(max = 25)
	private String emailId;

	@Pattern(regexp = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
	private String contactNumber;

	@NotBlank
	@Length(min = 8, max = 15)
	private String passcode;

	private LocalDateTime createdDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public User toUser() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailId(emailId);
		user.setContactNumber(contactNumber);
		user.setPasscode(encoder.encode(passcode));
		user.setCreatedDate(LocalDateTime.now());
		user.setRoles(Arrays.asList(new Role("USER")));
		return user;
	}

	public void updateUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailId(emailId);
		user.setContactNumber(contactNumber);

		if (!passcode.equals("DefaultPass")) {
			user.setPasscode(encoder.encode(passcode));
		}

	}
}
