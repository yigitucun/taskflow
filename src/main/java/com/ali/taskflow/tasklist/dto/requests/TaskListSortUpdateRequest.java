package com.ali.taskflow.tasklist.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TaskListSortUpdateRequest(
        @NotNull(message = "ID is required")
        Long id,

        @Min(value = 0, message = "Sort index must be 0 or greater")
        int sortIndex
) {}