package com.ali.taskflow.board.repository;

import com.ali.taskflow.board.entity.Board;
import com.ali.taskflow.board.projection.ListBoardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IBoardRepository extends JpaRepository<Board,Long> {
    @Query("SELECT MAX(b.sortIndex) FROM Board b WHERE b.project.id = :projectId")
    Integer findMaxSortIndexByProjectId(@Param("projectId") Long projectId);

    List<ListBoardProjection> findByProjectIdOrderBySortIndexAsc(@PathVariable("projectId") Long projectId);
}
