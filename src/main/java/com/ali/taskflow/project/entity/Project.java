package com.ali.taskflow.project.entity;

import com.ali.taskflow.shared.entity.BaseEntity;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.workspace.entity.Workspace;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Project() {
    }

    public Project(long id, Instant createdAt, Instant updatedAt, String name, Workspace workspace, User user) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.workspace = workspace;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
