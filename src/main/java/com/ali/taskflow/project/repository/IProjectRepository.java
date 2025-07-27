package com.ali.taskflow.project.repository;

import com.ali.taskflow.project.entity.Project;
import com.ali.taskflow.project.projection.ProjectBasicInfo;
import com.ali.taskflow.project.projection.ProjectListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p.id, p.workspace_id,p.user_id as ownerId FROM projects p WHERE p.id=:projectId ",nativeQuery = true)
    Optional<ProjectBasicInfo> findBasicInfoByProjectId(@Param("projectId") long projectId);

    @Query(value =  "SELECT p.id,p.name,p.user_id as ownerId,u.username as ownerUsername FROM projects p" +
                    " INNER JOIN workspaces w ON w.id=p.workspace_id" +
                    " INNER JOIN users u ON u.id=p.user_id" +
                    " WHERE w.id=:workspaceId",nativeQuery = true)
    List<ProjectListView> findAllByWorkspaceId(@Param("workspaceId")  long workspaceId);

}
