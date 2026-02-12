package com.info.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;


public class EmployeResponseDTO {
	
	
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true , nullable = false)
	@NotBlank(message = "Email is required")
	private String email;
	
	
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
