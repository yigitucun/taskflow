package com.ali.taskflow.taskassignee.dto.requests;

import jakarta.validation.constraints.NotNull;

public class CreateTaskAssigneeRequest {

    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    public CreateTaskAssigneeRequest() {
    }

    public CreateTaskAssigneeRequest(Long taskId, Long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    // Getters and Setters
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
}