package com.ali.taskflow.workspace.rule;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.workspace.repository.IWorkspaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceRule {
    private final IWorkspaceRepository workspaceRepository;

    public WorkspaceRule(IWorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }
    public void checkIfWorkspaceId(long id){
        if (!this.workspaceRepository.existsById(id)){
            throw new GlobalException("Workspace not found", HttpStatus.NOT_FOUND);
        }
    }
}
