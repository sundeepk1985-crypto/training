package com.examples.scart.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// Disable CSRF check
			.csrf().disable()
			// Authorize Requests
			.authorizeRequests()
			// Allow root request w/o authentication
			.antMatchers("/").permitAll()
			// Allow product requests
			.antMatchers("/products/**").permitAll()
			// Allow all requests
			.antMatchers("/**").permitAll()
			// Authenticate rest all requests
			.anyRequest().authenticated()
				.and()
					// Enables Basic Authentication to access from REST Client
					.httpBasic();
//				.and()
//					// Enables Form Authentication to access from browser
//					.formLogin();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
