package com.info.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(
	    name = "users")
public class User extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
	private UUID id;
	
	
	@Column(nullable = false)
	private String username;
	
	
	@Column(nullable = false)
    private String email;

	
	
	@Column(nullable = false)
	private String password;


	 
	 public UUID getId() { return id; }
	 
	 public void setId(UUID id) { this.id = id; }

	 public String getUsername() { return username; }

	 public void setUsername(String username) {this.username = username; }

	 public String getEmail() { return email; }

	 public void setEmail(String email) { this.email = email; }

	 public String getPassword() { return password;}

	 public void setPassword(String password) { this.password = password; }
	 
	 
	

}
