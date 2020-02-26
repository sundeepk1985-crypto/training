package com.examples.ipaas.controller;

import com.examples.ipaas.model.Incident;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidentController {

    @GetMapping
    public String home() {
        return "Welcome to Incidents Service!";
    }

    //GET /incidents
    public List<Incident> getIncidents() {
        return null;
    }

    //GET /incidents/{id}
    public Incident getIncident() {
        return null;
    }

    //POST /incidents
    public Incident createIncident() {
        return null;
    }

    //PUT /incidents/{id}
    public Incident updateIncident() {
        return null;
    }

    //DELETE /incidents/{id}
    public void deleteIncident() {

    }

}
