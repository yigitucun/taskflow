package com.ali.taskflow.workspaceMember.controller;

import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.workspaceMember.annotation.RequireWorkspaceRole;
import com.ali.taskflow.workspaceMember.dto.requests.CreateWorkspaceMemberRequest;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.service.WorkspaceMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/workspace-members")
@RestController
public class WorkspaceMemberController {
    private final WorkspaceMemberService workspaceMemberService;

    public WorkspaceMemberController(WorkspaceMemberService workspaceMemberService) {
        this.workspaceMemberService = workspaceMemberService;
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<?> findMembersByWorkspaceId(@PathVariable long workspaceId) {
        return ResponseEntity.ok(this.workspaceMemberService.findMembersByWorkspaceId(workspaceId));
    }

    @DeleteMapping("/{workspaceId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequireWorkspaceRole(roles = {Role.OWNER})
    public void deleteByUser(@PathVariable long workspaceId, @PathVariable long userId) {
        this.workspaceMemberService.deleteByUser(workspaceId,userId);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CreateWorkspaceMemberRequest request, @AuthenticationPrincipal SecurityUser user){
        return ResponseEntity.status(201).body(this.workspaceMemberService.add(request,user.getUser().getId()));
    }

}
