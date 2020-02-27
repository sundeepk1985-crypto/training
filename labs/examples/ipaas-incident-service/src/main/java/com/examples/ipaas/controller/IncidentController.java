package com.examples.ipaas.controller;

import com.examples.ipaas.model.Incident;
import com.examples.ipaas.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncidentController {

    @Autowired
    IncidentService service;

    @GetMapping
    public String home() {
        return "Welcome to Incidents Service!";
    }

    //GET /incidents
    @GetMapping(path = "/incidents", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Incident> getIncidents() {
        return service.getIncidents();
    }

    //GET /incidents/{id}
    @GetMapping(path = "/incidents/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Incident getIncident(@PathVariable int id) {
        return service.getIncident(id);
    }

    //POST /incidents
    @PostMapping(path = "/incidents", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Incident createIncident(@RequestBody  Incident incident) {
        return service.createIncident(incident);
    }

    //PUT /incidents/{id}
    @PutMapping(path = "/incidents/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Incident updateIncident(@PathVariable  int id, @RequestBody  Incident incident) {
        incident.setId(id);
        return service.updateIncident(id, incident);
    }

    //DELETE /incidents/{id}
    @DeleteMapping(path = "/incidents/{id}")
    public void deleteIncident(@PathVariable  int id) {
        service.deleteIncident(id);
    }

}
