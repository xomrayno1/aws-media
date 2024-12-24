package com.media.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyController {
	
	@GetMapping("/api/v1/healthcheck")
	public ResponseEntity<String> healthCheck(){
		return ResponseEntity.ok("healthy");
	}

}
