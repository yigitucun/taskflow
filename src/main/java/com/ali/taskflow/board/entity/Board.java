package com.ali.taskflow.board.entity;

import com.ali.taskflow.project.entity.Project;
import com.ali.taskflow.shared.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "boards")
public class Board extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    Project project;
    private int sortIndex;

    public Board(long id, Instant createdAt, Instant updatedAt, String name, Project project, int sortIndex) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.project = project;
        this.sortIndex = sortIndex;
    }

    public Board() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
