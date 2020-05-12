package com.examples.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@RibbonClient(name="incident-service")
@RestController
public class SpringCloudRibbonApplication implements CommandLineRunner{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibbonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// STEP 1: Discover incident services running with service id incident-service from Eureka
		// STEP 2: Load balance the request to different running instances
		String response = restTemplate.getForObject("http://incident-service/incidents", String.class);
		System.out.println(response);
	}

	@GetMapping("/tickets")
	public String getTickets() {

		// STEP 1: Discover incident services running with service id incident-service from Eureka
		// STEP 2: Load balance the request to different running instances
		String response = restTemplate.getForObject("http://incident-service/incidents", String.class);
		System.out.println(response);
		return response;
	}


}
