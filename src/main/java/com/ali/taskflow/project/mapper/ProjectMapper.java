package com.ali.taskflow.project.mapper;

import com.ali.taskflow.project.dto.requests.CreateProjectRequest;
import com.ali.taskflow.project.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(CreateProjectRequest request);
}
