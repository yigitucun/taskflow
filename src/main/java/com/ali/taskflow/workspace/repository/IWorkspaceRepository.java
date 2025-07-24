package com.ali.taskflow.workspace.repository;

import com.ali.taskflow.workspace.entity.Workspace;
import com.ali.taskflow.workspace.projection.ListWorkspaceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWorkspaceRepository extends JpaRepository<Workspace,Long> {

    boolean existsById(long id);

    @Query(value = "SELECT w.id,w.name,w.description,u.username as ownerUsername,u.id as ownerId,w.created_at,w.updated_at" +
            " FROM workspaces w" +
            " INNER JOIN  workspace_members wm ON wm.workspace_id=w.id " +
            " INNER JOIN users u ON u.id=wm.user_id " +
            "WHERE wm.role='OWNER'", nativeQuery = true)
    List<ListWorkspaceProjection> findAllBy();

}
