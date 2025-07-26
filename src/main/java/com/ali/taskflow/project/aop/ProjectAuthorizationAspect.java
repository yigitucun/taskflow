package com.ali.taskflow.project.aop;

import com.ali.taskflow.project.projection.ProjectBasicInfo;
import com.ali.taskflow.project.repository.IProjectRepository;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.repository.IWorkspaceMemberRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProjectAuthorizationAspect {

    private final IProjectRepository  projectRepository;
    private final IWorkspaceMemberRepository workspaceMemberRepository;

    public ProjectAuthorizationAspect(IProjectRepository projectRepository, IWorkspaceMemberRepository workspaceMemberRepository) {
        this.projectRepository = projectRepository;
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    @Before("@annotation(projectRole) && args(workspaceId,projectId,..)")
    public void checkProjectAuthorization(RequireProjectRole projectRole, long workspaceId, long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Role[] allowedRoles = projectRole.roles();

        ProjectBasicInfo project = this.projectRepository.findBasicInfoByProjectId(projectId)
                .orElseThrow(()->new GlobalException("project not found", HttpStatus.NOT_FOUND));

        String role = this.workspaceMemberRepository.findRoleByUserIdAndWorkspaceId(user.getUser().getId(),workspaceId)
                .orElseThrow(()->new GlobalException("you are not a member of this workspace",HttpStatus.FORBIDDEN));


        boolean authorized = Arrays.asList(allowedRoles).contains(Role.valueOf(role));

        if (!authorized || !project.getOwnerId().equals(user.getUser().getId())) {
            throw new GlobalException("You are not authorized to perform this action", HttpStatus.FORBIDDEN);
        }

    }

}
