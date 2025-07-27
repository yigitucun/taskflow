package com.ali.taskflow.tasklist.dto.responses;

public record TaskListResponse(
        Long id,
        String name,
        int sortIndex,
        Long boardId
) {}
