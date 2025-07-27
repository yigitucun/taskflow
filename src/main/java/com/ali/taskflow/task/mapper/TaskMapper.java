package com.ali.taskflow.task.mapper;

import com.ali.taskflow.task.dto.requests.TaskCreateRequest;
import com.ali.taskflow.task.dto.responses.TaskResponse;
import com.ali.taskflow.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "taskList.id", target = "taskListId")
    TaskResponse toResponse(Task task);

    Task toEntity(TaskCreateRequest request);

}

