package com.info.dto;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeDTO {
	
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true , nullable = false)
	@NotBlank(message = "Email is required")
	@Pattern(
		    regexp = "^(|.*[@_\\-].*)$",
		    message = "Email is incomplete "
		)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 16, message = "Password must be between 8 to 16 characters")
	@Pattern(
	    regexp = "^(|.*[@_\\-].*)$",
	    message = "Password must contain at least one special character (@, _, -)"
	)
	private String password;

	
	@Column(nullable = false)
	private double salary;
	
	@Column(nullable = false)
	private String department;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	

}
