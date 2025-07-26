package com.ali.taskflow.project.service;

import com.ali.taskflow.project.dto.requests.CreateProjectRequest;
import com.ali.taskflow.project.entity.Project;
import com.ali.taskflow.project.mapper.ProjectMapper;
import com.ali.taskflow.project.projection.ProjectBasicInfo;
import com.ali.taskflow.project.projection.ProjectListView;
import com.ali.taskflow.project.repository.IProjectRepository;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.user.repository.IUserRepository;
import com.ali.taskflow.workspace.repository.IWorkspaceRepository;
import com.ali.taskflow.workspace.rule.WorkspaceRule;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.repository.IWorkspaceMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final IUserRepository userRepository;
    private final WorkspaceRule workspaceRule;
    private final IWorkspaceRepository workspaceRepository;
    private final IWorkspaceMemberRepository workspaceMemberRepository;

    public ProjectService(IProjectRepository projectRepository, ProjectMapper projectMapper, IUserRepository userRepository, WorkspaceRule workspaceRule, IWorkspaceRepository workspaceRepository, IWorkspaceMemberRepository workspaceMemberRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
        this.workspaceRule = workspaceRule;
        this.workspaceRepository = workspaceRepository;
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    public List<ProjectListView> findAllByWorkspaceId(long workspaceId) {
        this.workspaceRule.checkIfWorkspaceId(workspaceId);
        return projectRepository.findAllByWorkspaceId(workspaceId);
    }

    public void deleteById(long projectId) {
        this.projectRepository.deleteById(projectId);
    }

    public CreateProjectRequest create(CreateProjectRequest request,long workspaceId,long ownerId){
        this.workspaceRule.checkIfWorkspaceId(workspaceId);

        Project project = projectMapper.toEntity(request);
        project.setUser(userRepository.getReferenceById(ownerId));
        project.setWorkspace(workspaceRepository.getReferenceById(workspaceId));

        projectRepository.save(project);
        return request;
    }


}
