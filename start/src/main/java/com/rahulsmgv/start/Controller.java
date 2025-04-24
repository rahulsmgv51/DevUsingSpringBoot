package com.rahulsmgv.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulsmgv.start.animal.Animal;

@RestController
public class Controller {
	
	@Autowired
	Animal dog;
	
	@GetMapping("/hello")
	public String healthCheck() {
		return dog.dog();
	}
}