package com.springsecurity.email.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.email.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder byCryptPasswordEncoder;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/api/registration/**").permitAll().anyRequest()
				.authenticated().and().formLogin();
		auth.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(byCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}

}
