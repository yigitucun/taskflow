package com.ali.taskflow.tasklist.repository;

import com.ali.taskflow.tasklist.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskListRepository extends JpaRepository<TaskList,Long> {
    @Query("SELECT MAX(t.sortIndex) FROM TaskList t WHERE t.board.id = :boardId")
    Integer findMaxSortIndexByBoardId(@Param("boardId") Long boardId);

    List<TaskList> findByBoardIdOrderBySortIndexAsc(Long boardId);
}
