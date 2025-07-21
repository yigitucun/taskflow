package com.ali.taskflow.user.dto.requests;

import com.ali.taskflow.shared.annotation.abstracts.UniqueEmail;
import com.ali.taskflow.shared.annotation.abstracts.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateUserRequest {
    @UniqueUsername
    @NotBlank(message = "required field")
    private String username;
    @UniqueEmail
    @Email(message = "not a valid email address")
    @NotBlank(message = "required field")
    private String email;
    @Length(min = 6,message = "must be at least 6 characters")
    @NotBlank(message = "required field")
    private String password;
    @NotBlank(message = "required field")
    private String fullName;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String username, String email, String password, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
