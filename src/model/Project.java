package model;

import java.util.UUID;

public class Project extends  BaseModel{
    private  String title;

    private UUID managerId;

    public Project(String title, UUID managerId) {
        this.title = title;
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }
}
