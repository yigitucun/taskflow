package com.ali.taskflow.taskassignee.repository;

import com.ali.taskflow.taskassignee.entity.TaskAssignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskAssigneeRepository extends JpaRepository<TaskAssignee, Long> {
    List<TaskAssignee> findByTaskId(Long taskId);
    List<TaskAssignee> findByUserId(Long userId);
    Optional<TaskAssignee> findByTaskIdAndUserId(Long taskId, Long userId);
}