package com.ali.taskflow.tasklist.entity;

import com.ali.taskflow.board.entity.Board;
import com.ali.taskflow.shared.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "task_lists")
public class TaskList extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private int sortIndex;

    public TaskList() {}

    public TaskList(long id, Instant createdAt, Instant updatedAt, String name, Board board, int sortIndex) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.board = board;
        this.sortIndex = sortIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
