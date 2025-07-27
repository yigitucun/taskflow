package com.ali.taskflow.task.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateRequest(
        @NotBlank(message = "Title cannot be blank")
        String title,

        String description,

        @NotNull(message = "TaskList ID is required")
        Long taskListId
){}
