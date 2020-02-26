package com.examples.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan
@Configuration
public class AppConfig {

    @Bean("bmw")
    public Car car() {
        return new Car(engine());
    }

    // <bean id="car" class="com.examples.spring.Car" >
    // <constructor-arg name="engine" ref="engine" />
    // </bean

    @Bean
    public Engine engine() {
        return new Engine();
    }
}
