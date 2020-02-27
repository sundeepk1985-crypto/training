package com.examples.ipaas.controller;

import com.examples.ipaas.model.ErrorMessage;
import com.examples.ipaas.model.Incident;
import com.examples.ipaas.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
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

//    //POST /incidents
//    @PostMapping(path = "/incidents", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public Incident createIncident(@RequestBody  Incident incident) {
//        return service.createIncident(incident);
//    }

    //POST /incidents
    //Return with status code 201 and resource location
    @PostMapping(path = "/incidents", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Incident> createIncident(@RequestBody @Valid Incident incident) {

        Incident createdIncident = service.createIncident(incident);

//        if(errors.hasErrors()) {
//            System.out.println("Validation Error Occured: " +  errors.getErrorCount());
//            return ResponseEntity.badRequest().body(null);
//        }

        // Build newly created Incident resource URI
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(incident.getId()).toUri();
        //e.g. http://localhost:8080/incidents/1

        return ResponseEntity.created(location).body(createdIncident);
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

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorMessage> handleValidationExcpetion(MethodArgumentNotValidException e) {
//
//        List<ObjectError> errors = e.getBindingResult().getAllErrors();
//        int size = errors.size();
//        StringBuilder errorMsgs = new StringBuilder();
//
//        for(int i = 0; i < size; i++ ) {
//            errorMsgs.append(errors.get(i).getDefaultMessage()).append(",");
//        }
//
//        ErrorMessage error = new ErrorMessage(400, "Validation Error", errorMsgs.toString());
//        return ResponseEntity.badRequest().body(error);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> handleError(Exception e) {
//        ErrorMessage error = new ErrorMessage(500, "Server Error", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
}
