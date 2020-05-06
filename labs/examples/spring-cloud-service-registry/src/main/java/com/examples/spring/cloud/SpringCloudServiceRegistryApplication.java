package com.examples.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer			// Runs this app as Eureka server
public class SpringCloudServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceRegistryApplication.class, args);
	}

}
