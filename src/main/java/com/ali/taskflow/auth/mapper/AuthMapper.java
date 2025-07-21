package com.ali.taskflow.auth.mapper;

import com.ali.taskflow.user.dto.requests.CreateUserRequest;
import com.ali.taskflow.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User toEntity(CreateUserRequest request);
}
