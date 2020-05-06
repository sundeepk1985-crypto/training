package com.examples.ipaas.respository;

import com.examples.ipaas.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class IncidentRepositoryImpl implements IncidentRepository {

    //Create Date source
    //Create Connection

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Incident create(Incident incident) {
        //create statement
        //execute query
        String query = "INSERT INTO INCIDENT(SUBMITTER, DESCRIPTION, PRIORITY, STATUS, CREATION_TIME) VALUES(?,?,?,?,?)";
        System.out.println(query);
        int count = jdbcTemplate.update(query, incident.getSubmitter(), incident.getDescription(), incident.getPriority(), incident.getStatus(), LocalDateTime.now());
        System.out.println(count + " Incident added");

        return incident;
    }

    @Override
    public Incident update(int id, Incident incident) {
        return null;
    }

    @Override
    public List<Incident> findAll() {
        List<Incident> incidents =  jdbcTemplate.query("SELECT * FROM INCIDENT", new IncidentMapper());
        return incidents;
    }

    @Override
    public Incident findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    private class IncidentMapper implements RowMapper<Incident>
    {

        @Override
        public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
            Incident incident = new Incident();
            incident.setId(rs.getInt("id"));
            incident.setSubmitter(rs.getString("submitter"));
            incident.setDescription(rs.getString("description"));
            incident.setPriority(rs.getString("priority"));
            incident.setStatus(rs.getString("status"));
            return incident;
        }
    }
}
