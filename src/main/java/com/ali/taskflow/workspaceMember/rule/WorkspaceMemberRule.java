package com.ali.taskflow.workspaceMember.rule;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.workspaceMember.repository.IWorkspaceMemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceMemberRule {
    private final IWorkspaceMemberRepository workspaceMemberRepository;

    public WorkspaceMemberRule(IWorkspaceMemberRepository workspaceMemberRepository) {
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    public void checkIfUserAlreadyMember(long userId,long workspaceId){
        if (this.workspaceMemberRepository.existsByUserId(userId,workspaceId))
            throw new GlobalException("user is already member", HttpStatus.CONFLICT);
    }
}
