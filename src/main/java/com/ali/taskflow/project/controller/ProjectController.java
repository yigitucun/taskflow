package com.ali.taskflow.project.controller;

import com.ali.taskflow.project.dto.requests.CreateProjectRequest;
import com.ali.taskflow.project.service.ProjectService;
import com.ali.taskflow.project.aop.RequireProjectRole;
import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.workspaceMember.aop.RequireWorkspaceRole;
import com.ali.taskflow.workspaceMember.enums.Role;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/workspace")
@RestController
public class ProjectController {

    private final ProjectService projectService;;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{workspaceId}/project")
    public ResponseEntity<?> findAllByWorkspaceId(@PathVariable("workspaceId") long workspaceId){
        return ResponseEntity.ok(projectService.findAllByWorkspaceId(workspaceId));
    }

    @DeleteMapping("/{workspaceId}/project/{projectId}")
    @ResponseStatus( HttpStatus.NO_CONTENT)
    @RequireProjectRole(roles = {Role.OWNER, Role.ADMIN})
    public void delete(
            @PathVariable long workspaceId,
            @PathVariable long projectId,
            @AuthenticationPrincipal SecurityUser securityUser) {
        this.projectService.deleteById(projectId);
    }

    @PostMapping("/{workspaceId}/project")
    @RequireWorkspaceRole(roles = {Role.OWNER, Role.ADMIN})
    public ResponseEntity<?> create(
                                    @PathVariable long  workspaceId,
                                    @AuthenticationPrincipal SecurityUser user,
                                    @Valid @RequestBody CreateProjectRequest request
                                    ) {
        return ResponseEntity.status(201).body(this.projectService.create(request,workspaceId,user.getUser().getId()));
    }

}
