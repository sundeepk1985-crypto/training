package com.examples.spring.boot;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car { //implements InitializingBean, DisposableBean {

	private String color = "white";
	private String model = "cd123";
	private String manufacturer = "bmw";

//	private Engine engine;
	
	public Car() {
		
	}
	
//	public Car(Engine engine) {
//		System.out.println("Car constructor called..");
//		this.engine = engine;
//	}
	
//	public static Car createInstance(Engine engine) {
//		Car car = new Car(engine);
//		return car;
//	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

//	public Engine getEngine() {
//		return engine;
//	}

//	public void setEngine(Engine engine) {
//		System.out.println("Car setter called..");
//		this.engine = engine;
//	}

	@PostConstruct
	public void init() {
		System.out.println("Car Bean Initialized...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Car Bean Destroyed...");
	}

//	public void afterPropertiesSet() throws Exception {
//		System.out.println("Car Bean Initialized...");		
//	}
//
//	public void destroy() throws Exception {
//		System.out.println("Car Bean Destroyed...");		
//	}
}
