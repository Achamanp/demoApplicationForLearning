package com.E_CommerceApplication.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
	private Integer id;
	 @Size(max = 50, message = "Name should not be longer than 50 characters")
	private String name;
	 @Email(message = "Email should be valid")
	    @NotBlank(message = "Email is mandatory")
	    @Column(unique = true)
	private String email;
	 @NotBlank(message = "Mobile number is mendatory")
	private String mobileNumber;
	 @NotBlank
	private String address;
	 @NotBlank(message = "Password is mandatory")
	    @Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
