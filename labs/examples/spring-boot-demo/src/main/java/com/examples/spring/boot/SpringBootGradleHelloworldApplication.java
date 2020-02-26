package com.examples.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @ComponentScan, @Configuration, @EnableAutoConfiguration
public class SpringBootGradleHelloworldApplication implements CommandLineRunner  {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGradleHelloworldApplication.class, args);
		System.out.println("Spring Boot Hello World!");
	}

	@Override
	public void run(String... args) {
			System.out.println("Context: " + context);

		Car bmw = (Car) context.getBean("bmw");

		bmw.setColor("black");

		System.out.println("Manufacturer: " + bmw.getManufacturer());
		System.out.println("Color: " + bmw.getColor());
		System.out.println("Capacity: " + bmw.getEngine().getCapacity());


	}
}
