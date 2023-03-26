package com.springsecurity.email.components;

public interface EmailSender {

	void send(String to, String email);
	
}
