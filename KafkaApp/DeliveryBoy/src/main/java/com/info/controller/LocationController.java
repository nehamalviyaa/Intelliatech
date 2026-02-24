package com.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.service.KafkaService;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private KafkaService service;
	
	
	public ResponseEntity<?> updateLocation(@RequestBody String location){
		
		service.updateLocation(location);
		return ResponseEntity.ok("Location updated");
		
	}

}
