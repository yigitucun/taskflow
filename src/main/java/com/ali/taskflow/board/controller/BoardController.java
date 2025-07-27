package com.ali.taskflow.board.controller;

import com.ali.taskflow.board.dto.requests.BoardSortUpdateRequest;
import com.ali.taskflow.board.dto.requests.CreateBoardRequest;
import com.ali.taskflow.board.service.BoardService;
import com.ali.taskflow.workspaceMember.aop.RequireWorkspaceRole;
import com.ali.taskflow.workspaceMember.enums.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspace")
public class BoardController {
    
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{projectId}/board")
    public ResponseEntity<?> getBoardsByProjectId(@PathVariable long projectId){
        return ResponseEntity.status(200).body(this.boardService.getBoardsByProjectId(projectId));
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderBoards(@RequestBody List<BoardSortUpdateRequest> updates) {
        boardService.updateBoardOrder(updates);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{workspaceId}/board")
    @RequireWorkspaceRole(roles = {Role.OWNER,Role.ADMIN})
    public ResponseEntity<?> createBoard(
            @PathVariable long workspaceId,
            @Valid @RequestBody CreateBoardRequest request) {
        return ResponseEntity.status(201).body(boardService.create(request));
    }
    
}
