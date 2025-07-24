package com.ali.taskflow.workspaceMember.dto.requests;

import com.ali.taskflow.workspaceMember.enums.Role;
import jakarta.validation.constraints.NotNull;

public class CreateWorkspaceMemberRequest {
    @NotNull
    private long workspaceId;
    @NotNull
    private Role role;

    public CreateWorkspaceMemberRequest() {
    }

    public CreateWorkspaceMemberRequest(long workspaceId, Role role) {
        this.workspaceId = workspaceId;
        this.role = role;
    }

    public long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(long workspaceId) {
        this.workspaceId = workspaceId;
    }



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
