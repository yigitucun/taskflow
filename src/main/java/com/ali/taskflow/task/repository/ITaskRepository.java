package com.ali.taskflow.task.repository;

import com.ali.taskflow.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByTaskListIdOrderBySortIndex(Long taskListId);

    @Query("SELECT MAX(t.sortIndex) FROM Task t WHERE t.taskList.id = :taskListId")
    Optional<Integer> findMaxSortIndexByTaskListId(Long taskListId);
}
