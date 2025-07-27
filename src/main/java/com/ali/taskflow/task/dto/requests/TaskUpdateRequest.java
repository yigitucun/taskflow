package com.ali.taskflow.task.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskUpdateRequest(
        @NotNull(message = "Task ID is required")
        Long id,

        @NotBlank(message = "Title cannot be blank")
        String title,

        String description
) {

}
