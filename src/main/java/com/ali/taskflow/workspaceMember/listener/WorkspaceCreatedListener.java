package com.ali.taskflow.workspaceMember.listener;

import com.ali.taskflow.user.repository.IUserRepository;
import com.ali.taskflow.workspace.event.WorkspaceCreatedEvent;
import com.ali.taskflow.workspace.repository.IWorkspaceRepository;
import com.ali.taskflow.workspaceMember.entity.WorkspaceMember;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.repository.IWorkspaceMemberRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceCreatedListener {
    private final IWorkspaceMemberRepository workspaceMemberRepository;
    private final IUserRepository userRepository;
    private final IWorkspaceRepository workspaceRepository;

    public WorkspaceCreatedListener(IWorkspaceMemberRepository workspaceMemberRepository, IUserRepository userRepository, IWorkspaceRepository workspaceRepository) {
        this.workspaceMemberRepository = workspaceMemberRepository;
        this.userRepository = userRepository;
        this.workspaceRepository = workspaceRepository;
    }

    @EventListener
    public void handleWorkspaceCreated(WorkspaceCreatedEvent event){
        WorkspaceMember member = new WorkspaceMember();
        member.setUser(userRepository.getReferenceById(event.userId()));
        member.setWorkspace(workspaceRepository.getReferenceById(event.workspaceId()));
        member.setRole(Role.OWNER);
        this.workspaceMemberRepository.save(member);
    }
}
