package com.springsecurity.email.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.email.entities.RegistrationRequest;
import com.springsecurity.email.services.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/registration")
@AllArgsConstructor
public class RegistrationResource {

	@Autowired
	private RegistrationService registrationService;
		
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
}
