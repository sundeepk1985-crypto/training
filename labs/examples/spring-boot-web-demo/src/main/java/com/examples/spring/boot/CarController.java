package com.examples.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    ApplicationContext context;

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "Welcome to Spring Boot REST";
    }

    @GetMapping(path = "/cars", produces = {"application/json"})
    public Car getCars(@RequestParam(required = false) String model, @RequestParam(required = false) String color) {
        System.out.println("Model: " + model + ", Color: " + color);
        Car bmw = (Car) context.getBean("bmw");
        return bmw;
    }

    @GetMapping(path = "/cars/{id}", produces = {"application/json"})
    public Car getCar(@PathVariable String id) {
        System.out.println("ID: " + id);
        Car bmw = (Car) context.getBean("bmw");
        return bmw;
    }

    @PostMapping(path = "/cars", consumes = {"application/json", "application/xml"}, produces = {"application/json;q=0.5", "application/xml;q=0.8"})
    public Car getCar(@RequestBody Car car) {
//        System.out.println("ID: " + id);
//        Car bmw = (Car) context.getBean("bmw");
        return car;
    }
}
