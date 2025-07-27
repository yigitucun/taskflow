package com.ali.taskflow.taskassignee.mapper;

import com.ali.taskflow.taskassignee.dto.requests.CreateTaskAssigneeRequest;
import com.ali.taskflow.taskassignee.dto.responses.TaskAssigneeResponse;
import com.ali.taskflow.taskassignee.entity.TaskAssignee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskAssigneeMapper {
    TaskAssignee toEntity(CreateTaskAssigneeRequest request);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    TaskAssigneeResponse toResponse(TaskAssignee taskAssignee);
}
