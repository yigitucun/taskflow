package com.ali.taskflow.auth.dto.responses;

public class TokeResponse {
    private String accessToken;

    public TokeResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokeResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
