package com.examples.scart.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Logic to match username against registered users and create UserDetails object goes here...
		System.out.println("Authentication request received with username - " + username);
		if("test".equals(username) ) {
			return new User("test", encoder.encode("password"), AuthorityUtils.createAuthorityList("ROLE_USER"));
		} else {
			// user name not matching
			return null;
		}
		
	}

}
