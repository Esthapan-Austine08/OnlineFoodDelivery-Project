package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

	@GetMapping
	public ResponseEntity<String> homeController(){
		return new ResponseEntity<String>("Hlo ,it a food delivery project",HttpStatus.OK);
	}
}
