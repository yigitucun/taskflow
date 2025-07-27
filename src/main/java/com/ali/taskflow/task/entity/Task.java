package com.ali.taskflow.task.entity;

import com.ali.taskflow.shared.entity.BaseEntity;
import com.ali.taskflow.tasklist.entity.TaskList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    private int sortIndex;

    @ManyToOne(optional = false)
    private TaskList taskList;

    public Task() {
    }

    public Task(long id, Instant createdAt, Instant updatedAt, String title, String description, int sortIndex, TaskList taskList) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.sortIndex = sortIndex;
        this.taskList = taskList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}