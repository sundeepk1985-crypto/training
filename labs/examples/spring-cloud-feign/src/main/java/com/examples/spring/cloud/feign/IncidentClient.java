package com.examples.spring.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "incident-service", url = "http://localhost:9092")
public interface IncidentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/incidents")
    List<Incident> getIncidents();

    @RequestMapping(method = RequestMethod.PUT, value = "/incidents/{id}", consumes = "application/json")
    Incident update(@PathVariable("id") Long id, Incident incident);
}
