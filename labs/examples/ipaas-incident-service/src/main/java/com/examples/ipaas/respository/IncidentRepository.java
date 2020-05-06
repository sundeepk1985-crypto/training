package com.examples.ipaas.respository;

import com.examples.ipaas.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findByPriority(String priority);
    //SELECT * FROM INCIDENT WHERE PRIORITY = 'LOW'

    List<Incident> findByPriorityAndStatus(String priority, String status);

//    @Query("SELECT i FROM INCIDENT i WHERE PRIORITY=:priority AND STATUS=:status")
//    List<Incident> findAllLowPriorityOpenTickets(String priority, String status);

//    public Incident create(Incident incident);
//    public Incident update(int id, Incident incident);
//    public List<Incident> findAll();
//    public Incident findById(int id);
//    public void delete(int id);
}
