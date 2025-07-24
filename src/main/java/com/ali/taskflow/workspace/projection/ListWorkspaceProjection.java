package com.ali.taskflow.workspace.projection;

import java.util.Date;

public interface ListWorkspaceProjection {
    Long getId();
    String getName();
    String getDescription();
    String getOwnerUsername();
    Long getOwnerId();
    Date getCreatedAt();
    Date getUpdatedAt();
}
