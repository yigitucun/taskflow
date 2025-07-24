package com.ali.taskflow.workspaceMember.projection;

import com.ali.taskflow.workspaceMember.enums.Role;

public interface ListMemberByWorkspace {
     User getUser();
     Role getRole();

     interface User{
         Long getId();
         String getUsername();
         String getEmail();
         String getFullName();
     }
}
