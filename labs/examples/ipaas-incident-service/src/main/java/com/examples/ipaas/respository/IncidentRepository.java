package com.examples.ipaas.respository;

import com.examples.ipaas.model.Incident;

import java.util.List;

public interface IncidentRepository {
    public Incident create(Incident incident);
    public Incident update(int id, Incident incident);
    public List<Incident> findAll();
    public Incident findById(int id);
    public void delete(int id);
}
