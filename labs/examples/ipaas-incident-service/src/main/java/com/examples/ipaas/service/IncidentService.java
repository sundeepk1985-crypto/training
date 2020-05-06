package com.examples.ipaas.service;

import com.examples.ipaas.model.Incident;
import com.examples.ipaas.respository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncidentService {

//    Map<Integer, Incident> incidents = new HashMap<>();
    @Autowired
    IncidentRepository repo;

    public List<Incident> getIncidents() {
//        return new ArrayList(incidents.values());
//        return repo.findAll();
        return repo.findAll();
    }

    public Incident getIncident(int id) {
//        return incidents.get(id);
//        return repo.findById(id);
        return repo.findById(id).get();
    }



    public Incident createIncident(Incident incident) {
//        int incidentId = incidents.size() + 1;
//        incident.setId(incidentId);
//        incident.setCreationTime(LocalDateTime.now());
//        incidents.put(incidentId, incident);
//        System.out.println("Incident created " + incident);
//        return incident;

//        return repo.create(incident);

        repo.save(incident);

        return incident;

    }

    public Incident updateIncident(int id, Incident incident) {
//        incidents.put(id, incident);
//        return incident;
//        return repo.update(id, incident);

        repo.save(incident);

        return incident;
    }

    public void deleteIncident(int id) {
//        incidents.remove(id);
//        repo.delete(id);
        Incident incidentForDelete = repo.findById(id).get();
        repo.delete(incidentForDelete);
    }


}
