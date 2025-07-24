package com.ali.taskflow.workspace.entity;

import com.ali.taskflow.shared.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "workspaces")
public class Workspace extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Workspace(long id, Instant createdAt, Instant updatedAt, String name, String description) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.description = description;
    }

    public Workspace() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
