package com.ali.taskflow.tasklist.mapper;

import com.ali.taskflow.tasklist.dto.requests.TaskListCreateRequest;
import com.ali.taskflow.tasklist.dto.requests.TaskListUpdateRequest;
import com.ali.taskflow.tasklist.dto.responses.TaskListResponse;
import com.ali.taskflow.tasklist.entity.TaskList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {

    TaskList toEntity(TaskListCreateRequest dto);

    TaskListResponse toResponse(TaskList entity);

    @Mapping(target = "board", ignore = true)
    TaskList toEntity(TaskListUpdateRequest request);

    List<TaskListResponse> toResponseList(List<TaskList> entities);
}
