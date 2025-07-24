package com.ali.taskflow.workspace.service;

import com.ali.taskflow.user.rule.UserRule;
import com.ali.taskflow.workspace.dto.requests.CreateWorkspaceRequest;
import com.ali.taskflow.workspace.entity.Workspace;
import com.ali.taskflow.workspace.event.WorkspaceCreatedEvent;
import com.ali.taskflow.workspace.mapper.WorkspaceMapper;
import com.ali.taskflow.workspace.projection.ListWorkspaceProjection;
import com.ali.taskflow.workspace.repository.IWorkspaceRepository;
import com.ali.taskflow.workspaceMember.rule.WorkspaceMemberRule;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {
    private final IWorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRule userRule;
    private final WorkspaceMemberRule workspaceMemberRule;


    public WorkspaceService(IWorkspaceRepository workspaceRepository, WorkspaceMapper workspaceMapper, ApplicationEventPublisher eventPublisher, UserRule userRule, WorkspaceMemberRule workspaceMemberRule) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceMapper = workspaceMapper;
        this.eventPublisher = eventPublisher;
        this.userRule = userRule;
        this.workspaceMemberRule = workspaceMemberRule;
    }

    public CreateWorkspaceRequest create(CreateWorkspaceRequest request,long userId){
        this.userRule.checkIfUserId(userId);
        Workspace workspace = this.workspaceRepository.save(workspaceMapper.toEntity(request));
        eventPublisher.publishEvent(new WorkspaceCreatedEvent(workspace.getId(),userId));
        return request;
    }

    public List<ListWorkspaceProjection> getAll(){
        return this.workspaceRepository.findAllBy();
    }

}
