package com.examples.ipaas.controller;

import com.examples.ipaas.model.Ticket;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    ProducerTemplate producer;

    //POST /tickets

    @PostMapping("/tickets")
    public ResponseEntity createTicket(Ticket ticket) {

        System.out.println("Ticket details: " + ticket);

        // Invoke Camel Route
        producer.sendBody(ticket);

        return ResponseEntity.ok("Success");
    }
}
