package com.info.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


 
public class UserRequestDTO {
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true , nullable = false)
	@NotBlank(message = "Email is required")
	@Pattern(
		    regexp = "^(|.*[@_\\-].*)$",
		    message = "Email is incomplete "
		)
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
