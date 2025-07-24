package com.ali.taskflow.workspaceMember.entity;

import com.ali.taskflow.shared.entity.BaseEntity;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.workspace.entity.Workspace;
import com.ali.taskflow.workspaceMember.enums.Role;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "workspace_members")
public class WorkspaceMember extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Workspace workspace;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
    @Enumerated(EnumType.STRING)
    private Role role;

    public WorkspaceMember(long id, Instant createdAt, Instant updatedAt, Workspace workspace, User user, Role role) {
        super(id, createdAt, updatedAt);
        this.workspace = workspace;
        this.user = user;
        this.role = role;
    }

    public WorkspaceMember() {
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
