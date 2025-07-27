package com.ali.taskflow.task.dto.responses;

public record TaskResponse(
        Long id,
        String title,
        String description,
        int sortIndex,
        Long taskListId
) {}