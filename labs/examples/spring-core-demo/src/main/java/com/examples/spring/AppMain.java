package com.examples.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

	public static void main(String[] args)
	{
//		Car bmw = new Car();
//		bmw.setColor("White");
//		bmw.setManufacturer("BMW");
//		bmw.setModel("XA");
//
//		Engine vti = new Engine();
//		vti.setCapacity(2000);
//		vti.setNoOfCynclinders(4);
//		vti.setTorque(10);
//
//		Engine vtix = new Engine();
//		vtix.setCapacity(4000);
//		vtix.setNoOfCynclinders(4);
//		vtix.setTorque(10);
//
//		bmw.setEngine(vti);
//
//		System.out.println("Manufacturer: " + bmw.getManufacturer());
//		System.out.println("Color: " + bmw.getColor());
//		System.out.println("Capacity: " + bmw.getEngine().getCapacity());
		
//		// Instantiate IoC container
		// Approach #1: XML based configuration
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");

		// Approach #2: Annotation based configuration
//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Approach #3: Java based configuration
//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
//		System.out.println("No of beans: " + context.getBeanDefinitionCount());
//		for (String beanName : context.getBeanDefinitionNames()) {
//			System.out.println(beanName);
//		}
		
		Car bmw = (Car) context.getBean("bmw");

		bmw.setColor("black");

		System.out.println("Manufacturer: " + bmw.getManufacturer());
		System.out.println("Color: " + bmw.getColor());
		System.out.println("Capacity: " + bmw.getEngine().getCapacity());

		context.registerShutdownHook();
		
	}
}