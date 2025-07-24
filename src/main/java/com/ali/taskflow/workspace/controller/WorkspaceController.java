package com.ali.taskflow.workspace.controller;

import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.workspace.dto.requests.CreateWorkspaceRequest;
import com.ali.taskflow.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<CreateWorkspaceRequest> create(@Valid @RequestBody CreateWorkspaceRequest request, @AuthenticationPrincipal SecurityUser user) {
        return ResponseEntity.status(201).body(this.workspaceService.create(request,user.getUser().getId()));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.workspaceService.getAll());
    }

}
