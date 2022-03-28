package com.tracker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String project_name, status_project;
    public int priority_project;
    private String start_date, completion_date;

    public Project(String project_name, String status_project, int priority_project, String start_date, String completion_date) {
        this.project_name = project_name;
        this.status_project = status_project;
        this.priority_project = priority_project;
        this.start_date = start_date;
        this.completion_date = completion_date;
    }

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStatus() {
        return status_project;
    }

    public void setStatus(String status_project) {
        this.status_project = status_project;
    }

    public int getPriority() {
        return priority_project;
    }

    public void setPriority(int priority_project) {
        this.priority_project = priority_project;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(String completion_date) {
        this.completion_date = completion_date;
    }
}
