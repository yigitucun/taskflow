package com.ali.taskflow.taskComment.mapper;

import com.ali.taskflow.taskComment.dto.requests.CreateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.requests.UpdateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.responses.TaskCommentResponse;
import com.ali.taskflow.taskComment.entity.TaskComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

    TaskComment toEntity(CreateTaskCommentRequest request);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userName")
    TaskCommentResponse toResponse(TaskComment taskComment);

    void updateTaskCommentFromDto(UpdateTaskCommentRequest dto, @MappingTarget TaskComment entity);
}

