package com.ali.taskflow.board.dto.requests;

public record BoardSortUpdateRequest(
        Long id,
        int sortIndex
) {}
