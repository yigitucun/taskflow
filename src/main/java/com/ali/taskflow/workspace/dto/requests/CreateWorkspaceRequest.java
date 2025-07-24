package com.ali.taskflow.workspace.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class CreateWorkspaceRequest {
    @NotBlank
    private String name;
    private String description;

    public CreateWorkspaceRequest() {
    }

    public CreateWorkspaceRequest(String name, String description ){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
