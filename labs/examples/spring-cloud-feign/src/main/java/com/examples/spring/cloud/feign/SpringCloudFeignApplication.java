package com.examples.spring.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class SpringCloudFeignApplication implements CommandLineRunner {

	@Autowired
	IncidentClient client;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Feign client: " + client);
		System.out.println(client.getIncidents());
	}

	@GetMapping("/tickets")
	public ResponseEntity<Object> getTickets() {
		System.out.println(client.getIncidents());
		return ResponseEntity.ok().body(client.getIncidents());
	}

}
