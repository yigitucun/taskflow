package com.ali.taskflow.workspaceMember.repository;

import com.ali.taskflow.workspaceMember.entity.WorkspaceMember;
import com.ali.taskflow.workspaceMember.enums.Role;
import com.ali.taskflow.workspaceMember.projection.ListMemberByWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWorkspaceMemberRepository extends JpaRepository<WorkspaceMember,Long> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM workspace_members WHERE user_id=:userId AND workspace_id=:workspaceId)",nativeQuery = true)
    boolean existsByUserId(@Param("userId") long userId, @Param("workspaceId") long workspaceId);

    List<ListMemberByWorkspace> findMembersByWorkspaceId(long workspaceId);

    @Query(value = "SELECT role FROM workspace_members WHERE user_id = :userId AND workspace_id = :workspaceId", nativeQuery = true)
    Optional<String> findRoleByUserIdAndWorkspaceId(@Param("userId") Long userId, @Param("workspaceId") Long workspaceId);


    @Modifying
    @Transactional
    @Query("DELETE FROM WorkspaceMember wm WHERE wm.user.id = :userId AND wm.workspace.id = :workspaceId")
    void deleteByUserIdAndWorkspaceId(@Param("userId") Long userId, @Param("workspaceId") Long workspaceId);


}
