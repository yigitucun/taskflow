package com.ali.taskflow.workspaceMember.service;

import com.ali.taskflow.user.repository.IUserRepository;
import com.ali.taskflow.user.rule.UserRule;
import com.ali.taskflow.workspace.repository.IWorkspaceRepository;
import com.ali.taskflow.workspace.rule.WorkspaceRule;
import com.ali.taskflow.workspaceMember.dto.requests.CreateWorkspaceMemberRequest;
import com.ali.taskflow.workspaceMember.entity.WorkspaceMember;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.mapper.WorkspaceMemberMapper;
import com.ali.taskflow.workspaceMember.projection.ListMemberByWorkspace;
import com.ali.taskflow.workspaceMember.repository.IWorkspaceMemberRepository;
import com.ali.taskflow.workspaceMember.rule.WorkspaceMemberRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceMemberService {
    private final IWorkspaceMemberRepository workspaceMemberRepository;
    private final IWorkspaceRepository workspaceRepository;
    private final WorkspaceRule workspaceRule;
    private final UserRule userRule;
    private final WorkspaceMemberMapper workspaceMemberMapper;
    private final IUserRepository userRepository;
    private final WorkspaceMemberRule workspaceMemberRule;

    public WorkspaceMemberService(IWorkspaceMemberRepository workspaceMemberRepository, IWorkspaceRepository workspaceRepository, WorkspaceRule workspaceRule, UserRule userRule, WorkspaceMemberMapper workspaceMemberMapper, IUserRepository userRepository, WorkspaceMemberRule workspaceMemberRule) {
        this.workspaceMemberRepository = workspaceMemberRepository;
        this.workspaceRepository = workspaceRepository;
        this.workspaceRule = workspaceRule;
        this.userRule = userRule;
        this.workspaceMemberMapper = workspaceMemberMapper;
        this.userRepository = userRepository;
        this.workspaceMemberRule = workspaceMemberRule;
    }

    public void deleteByUser(long workspaceId,long userId){
        this.userRule.checkIfUserId(userId);
        this.workspaceMemberRepository.deleteByUserIdAndWorkspaceId(userId,workspaceId);
    }

    public List<ListMemberByWorkspace> findMembersByWorkspaceId(long workspaceId){
        return this.workspaceMemberRepository.findMembersByWorkspaceId(workspaceId);
    }

    public CreateWorkspaceMemberRequest add(CreateWorkspaceMemberRequest request,long userId){
        this.userRule.checkIfUserId(userId);
        this.workspaceRule.checkIfWorkspaceId(request.getWorkspaceId());
        this.workspaceMemberRule.checkIfUserAlreadyMember(userId,request.getWorkspaceId());
        WorkspaceMember workspaceMember = this.workspaceMemberMapper.toEntity(request);
        workspaceMember.setWorkspace(this.workspaceRepository.getReferenceById(request.getWorkspaceId()));
        workspaceMember.setUser(this.userRepository.getReferenceById(userId));

        this.workspaceMemberRepository.save(workspaceMember);
        return request;
    }


}
