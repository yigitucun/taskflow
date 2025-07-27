package com.ali.taskflow.task.controller;

import com.ali.taskflow.task.dto.requests.TaskCreateRequest;
import com.ali.taskflow.task.dto.requests.TaskUpdateRequest;
import com.ali.taskflow.task.dto.responses.TaskResponse;
import com.ali.taskflow.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskCreateRequest request) {
        return ResponseEntity.ok(taskService.create(request));
    }

    @GetMapping("/list/{taskListId}")
    public ResponseEntity<List<TaskResponse>> getAll(@PathVariable Long taskListId) {
        return ResponseEntity.ok(taskService.getAllByTaskListId(taskListId));
    }

    @PutMapping
    public ResponseEntity<TaskResponse> update(@RequestBody @Valid TaskUpdateRequest request) {
        return ResponseEntity.ok(taskService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
