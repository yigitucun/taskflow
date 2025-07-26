package com.ali.taskflow.workspaceMember.aop;

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
public class WorkspaceAuthorizationAspect {

    private final IWorkspaceMemberRepository workspaceMemberRepository;

    public WorkspaceAuthorizationAspect(IWorkspaceMemberRepository workspaceMemberRepository) {
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    @Before("@annotation(requireWorkspaceRole) && args(workspaceId,..)")
    public void checkWorkspaceRole(RequireWorkspaceRole requireWorkspaceRole, long workspaceId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Role[] allowedRoles = requireWorkspaceRole.roles();
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        String role = workspaceMemberRepository.findRoleByUserIdAndWorkspaceId(user.getUser().getId(), workspaceId)
                .orElseThrow(()->new GlobalException("you are not a member of this workspace", HttpStatus.FORBIDDEN));


        boolean authorized = Arrays.asList(allowedRoles).contains(Role.valueOf(role));

        if (!authorized) {
            throw new GlobalException("You are not authorized to perform this action", HttpStatus.FORBIDDEN);
        }

    }

}
