package com.examples.ipaas.respository;

import com.examples.ipaas.model.Incident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

//@Repository
//@Transactional
public class IncidentRepositoryJpaImpl { //implements IncidentRepository {

//    private static Logger log = LoggerFactory.getLogger(IncidentRepositoryJpaImpl.class);
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public Incident create(Incident incident) {
//        entityManager.persist(incident);
//        log.info("Incident added {}", incident);
//        return incident;
//    }
//
//    @Override
//    public Incident update(int id, Incident incident) {
////        Incident incidentForUpdate = entityManager.find(Incident.class, id);
////        incidentForUpdate.setSubmitter(incident.getSubmitter());
////        incidentForUpdate.setDescription(incident.getDescription());
////
////        entityManager.persist(incidentForUpdate);
//
//        entityManager.merge(incident);
//        log.info("Incident updated {}", incident);
//        return  entityManager.find(Incident.class, id);
//    }
//
//    @Override
//    public List<Incident> findAll() {
//        return entityManager.createQuery("SELECT e FROM INCIDENT e", Incident.class).getResultList();
//    }
//
//    @Override
//    public Incident findById(int id) {
//        Incident incident = entityManager.find(Incident.class, id);
//        log.info("Fetched incident detail {}", incident);
//        return incident;
//    }
//
//    @Override
//    public void delete(int id) {
//        Incident incidentForDelete = entityManager.find(Incident.class, id);
//        log.info("Incident deleted {}", incidentForDelete);
//        entityManager.remove(incidentForDelete);
//
//    }
}
