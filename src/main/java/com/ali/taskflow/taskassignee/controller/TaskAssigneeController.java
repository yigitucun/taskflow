package com.ali.taskflow.taskassignee.controller;


import com.ali.taskflow.taskassignee.dto.requests.CreateTaskAssigneeRequest;
import com.ali.taskflow.taskassignee.dto.responses.TaskAssigneeResponse;
import com.ali.taskflow.taskassignee.service.TaskAssigneeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-assignees")
public class TaskAssigneeController {

    private final TaskAssigneeService taskAssigneeService;

    public TaskAssigneeController(TaskAssigneeService taskAssigneeService) {
        this.taskAssigneeService = taskAssigneeService;
    }

    @PostMapping
    public ResponseEntity<TaskAssigneeResponse> assignTask(@Valid @RequestBody CreateTaskAssigneeRequest request) {
        TaskAssigneeResponse response = taskAssigneeService.assignTaskToUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TaskAssigneeResponse>> getAssigneesByTaskId(@PathVariable Long taskId) {
        List<TaskAssigneeResponse> response = taskAssigneeService.getAssigneesForTask(taskId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/task/{taskId}/user/{userId}")
    public ResponseEntity<Void> unassignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        taskAssigneeService.unassignTaskFromUser(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}