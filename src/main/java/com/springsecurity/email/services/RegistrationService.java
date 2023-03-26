package com.springsecurity.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.email.entities.RegistrationRequest;

@Service	
public class RegistrationService {

	@Autowired
	private EmailValidator emailValidator;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		
		if (!isValidEmail) {
			throw new IllegalStateException("Email not valid!");
		}
		
		return "Works";
	}

}
