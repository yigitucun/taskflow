package com.ali.taskflow.project.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class CreateProjectRequest {
    @NotBlank
    private String name;

    public CreateProjectRequest() {
    }

    public CreateProjectRequest(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
