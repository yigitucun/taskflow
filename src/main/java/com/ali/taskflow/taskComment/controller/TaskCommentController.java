package com.ali.taskflow.taskComment.controller;

import com.ali.taskflow.taskComment.dto.requests.CreateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.requests.UpdateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.responses.TaskCommentResponse;
import com.ali.taskflow.taskComment.service.TaskCommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-comments")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping
    public ResponseEntity<TaskCommentResponse> createComment(@Valid @RequestBody CreateTaskCommentRequest request) {
        TaskCommentResponse response = taskCommentService.createComment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TaskCommentResponse>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<TaskCommentResponse> response = taskCommentService.getCommentsForTask(taskId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCommentResponse> getCommentById(@PathVariable Long id) {
        TaskCommentResponse response = taskCommentService.getCommentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCommentResponse> updateComment(@PathVariable Long id, @Valid @RequestBody UpdateTaskCommentRequest request) {
        TaskCommentResponse response = taskCommentService.updateComment(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        taskCommentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}