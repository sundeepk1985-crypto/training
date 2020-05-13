package com.examples.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudServiceDiscoveryApplication implements CommandLineRunner {

	@Autowired
	DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceDiscoveryApplication.class, args);
	}

	// TODO: Write logic to discover the product service and invoke

	@Override
	public void run(String... args) throws Exception {

		System.out.println(discoveryClient);

		List<ServiceInstance> instances = discoveryClient.getInstances("incident-service");
		System.out.println("No of instances: " + instances.size());
		if (instances.size() > 0) {
			// get base uri of product-service -> http://localhost:9092
			URI baseUri = instances.get(0).getUri();
			baseUri = new URI(baseUri +"/incidents");
			// http://localhost:9092/incidents
			System.out.println(baseUri.toString());

			RestTemplate client = new RestTemplate();

			RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET, baseUri);
			ResponseEntity<String> response = client.exchange(request, String.class);

			System.out.println(response.getBody());
		}
	}


}
