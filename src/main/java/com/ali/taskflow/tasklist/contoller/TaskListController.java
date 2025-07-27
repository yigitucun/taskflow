package com.ali.taskflow.tasklist.contoller;

import com.ali.taskflow.tasklist.dto.requests.TaskListCreateRequest;
import com.ali.taskflow.tasklist.dto.requests.TaskListSortUpdateRequest;
import com.ali.taskflow.tasklist.dto.requests.TaskListUpdateRequest;
import com.ali.taskflow.tasklist.dto.responses.TaskListResponse;
import com.ali.taskflow.tasklist.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> create(@Valid @RequestBody TaskListCreateRequest request) {
        TaskListResponse response = taskListService.createTaskList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<TaskListResponse>> getByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskListService.getTaskListsByBoardId(boardId));
    }

    @PutMapping
    public ResponseEntity<TaskListResponse> update(@Valid @RequestBody TaskListUpdateRequest request) {
        return ResponseEntity.ok(taskListService.updateTaskList(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorder(@Valid @RequestBody List<@Valid TaskListSortUpdateRequest> updates) {
        taskListService.updateTaskListOrder(updates);
        return ResponseEntity.noContent().build();
    }
}
