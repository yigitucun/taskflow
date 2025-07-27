package com.ali.taskflow.board.dto.requests;

public class CreateBoardRequest {
    private String name;
    private Long sortIndex;
    private long projectId;

    public CreateBoardRequest() {
    }

    public CreateBoardRequest(String name, Long sortIndex, long projectId) {
        this.name = name;
        this.sortIndex = sortIndex;
        this.projectId = projectId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }
}
