package model;

import java.util.UUID;

public abstract class BaseModel {
    {
        this.id= UUID.randomUUID();
    }
    protected  UUID id;
    protected boolean isActive=true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
