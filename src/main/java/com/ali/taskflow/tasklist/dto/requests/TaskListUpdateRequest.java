package com.ali.taskflow.tasklist.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskListUpdateRequest(
        @NotNull(message = "ID is required")
        Long id,

        @NotBlank(message = "Name must not be blank")
        String name
) {}