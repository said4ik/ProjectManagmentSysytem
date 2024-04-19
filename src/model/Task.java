package model;

import enam.Role;
import enam.Status;

import java.util.UUID;

public class Task extends BaseModel {
    private String title;

    private UUID projectId;

    private UUID employerId;


    private Status status = Status.CREATED;


    public Task(String title, UUID projectId, UUID employerId, Status status) {
        this.title = title;
        this.projectId = projectId;
        this.employerId = employerId;
        this.status = status;
    }

    public Task(String title, UUID projectId) {
        this.title = title;
        this.projectId = projectId;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getEmployerId() {
        return employerId;
    }

    public void setEmployerId(UUID employerId) {
        this.employerId = employerId;
    }

    public Status getStatus() {
        return status;
    }



    public void setStatus(Status status) {
        this.status = status;
    }
}
