package com.examples.ipaas.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Entity
@Table(name = "INCIDENT")
public class Incident {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "Submitter can't be empty")
    private String submitter;
    @NotEmpty(message = "Description can't be empty")
    private String description;
    private String priority;
    private String status;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;
    @Column(name = "resolution_time")
    private LocalDateTime resolutionTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getResolutionTime() {
        return resolutionTime;
    }

    public void setResolutionTime(LocalDateTime resolutionTime) {
        this.resolutionTime = resolutionTime;
    }

    public String toString() {
        return "ID: " + this.getId() + ", Description: " + this.getDescription();
    }
}
