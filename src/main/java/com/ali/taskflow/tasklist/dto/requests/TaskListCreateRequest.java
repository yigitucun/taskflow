package com.ali.taskflow.tasklist.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskListCreateRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Board ID is required")
        Long boardId
) {}