package com.ali.taskflow.taskComment.dto.responses;

import java.time.Instant;
import java.time.LocalDateTime;

public class TaskCommentResponse {
    private Long id;
    private Long taskId;
    private Long userId;
    private String userName;
    private String content;
    private Instant createdAt;

    public TaskCommentResponse() {
    }

    public TaskCommentResponse(Long id, Long taskId, Long userId, String userName, String content, Instant createdAt) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}