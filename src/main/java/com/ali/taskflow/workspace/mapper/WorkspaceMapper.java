package com.ali.taskflow.workspace.mapper;

import com.ali.taskflow.workspace.dto.requests.CreateWorkspaceRequest;
import com.ali.taskflow.workspace.entity.Workspace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace toEntity(CreateWorkspaceRequest request);
}
