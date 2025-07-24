package com.ali.taskflow.workspaceMember.mapper;

import com.ali.taskflow.workspaceMember.dto.requests.CreateWorkspaceMemberRequest;
import com.ali.taskflow.workspaceMember.entity.WorkspaceMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMemberMapper {
    WorkspaceMember toEntity(CreateWorkspaceMemberRequest request);
}

