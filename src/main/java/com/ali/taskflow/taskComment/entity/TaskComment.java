package com.ali.taskflow.taskComment.entity;

import com.ali.taskflow.shared.entity.BaseEntity;
import com.ali.taskflow.task.entity.Task;
import com.ali.taskflow.user.entity.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "task_commenst")
public class TaskComment extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public TaskComment() {
    }

    public TaskComment(Task task, User user, String content) {
        this.task = task;
        this.user = user;
        this.content = content;
    }

    public TaskComment(long id, Instant createdAt, Instant updatedAt, Task task, User user, String content) {
        super(id, createdAt, updatedAt);
        this.task = task;
        this.user = user;
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}