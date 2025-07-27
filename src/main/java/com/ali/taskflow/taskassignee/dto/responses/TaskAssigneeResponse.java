package com.ali.taskflow.taskassignee.dto.responses;

public class TaskAssigneeResponse {
    private Long id;
    private Long taskId;
    private Long userId;

    public TaskAssigneeResponse() {
    }

    public TaskAssigneeResponse(Long id, Long taskId, Long userId) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
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

}