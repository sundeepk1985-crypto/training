package com.examples.spring.boot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("vk2")
@Primary
public class SpecialEngine extends Engine { //implements InitializingBean, DisposableBean {

	private int capacity = 4000;
	private int noOfCynclinders = 4;
	private int torque = 1000;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNoOfCynclinders() {
		return noOfCynclinders;
	}

	public void setNoOfCynclinders(int noOfCynclinders) {
		this.noOfCynclinders = noOfCynclinders;
	}

	public int getTorque() {
		return torque;
	}

	public void setTorque(int torque) {
		this.torque = torque;
	}
	
	public void init() {
		System.out.println("Engine Bean Initialized...");
	}
	
	public void destroy() {
		System.out.println("Engine Bean Destroyed...");
	}	

}
