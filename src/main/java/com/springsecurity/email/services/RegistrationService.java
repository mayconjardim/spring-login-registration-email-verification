package com.springsecurity.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.email.entities.RegistrationRequest;
import com.springsecurity.email.entities.User;
import com.springsecurity.email.enums.UserRole;

@Service
public class RegistrationService {

	@Autowired
	private EmailValidatorService emailValidator;

	@Autowired
	private UserService userService;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail) {
			throw new IllegalStateException("Email not valid!");
		}

		return userService.signUpUser(new User(request.getFirstName(), request.getLastName(), request.getEmail(),
				request.getPassword(), UserRole.USER));
	}

}
