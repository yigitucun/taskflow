package com.ali.taskflow.workspaceMember.aop;

import com.ali.taskflow.workspaceMember.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireWorkspaceRole {
    Role[] roles();
}
