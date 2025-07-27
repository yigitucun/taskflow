package com.ali.taskflow.board.service;

import com.ali.taskflow.board.dto.requests.BoardSortUpdateRequest;
import com.ali.taskflow.board.dto.requests.CreateBoardRequest;
import com.ali.taskflow.board.entity.Board;
import com.ali.taskflow.board.mapper.BoardMapper;
import com.ali.taskflow.board.projection.ListBoardProjection;
import com.ali.taskflow.board.repository.IBoardRepository;
import com.ali.taskflow.project.projection.ProjectBasicInfo;
import com.ali.taskflow.project.repository.IProjectRepository;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final IBoardRepository boardRepository;
    private final IProjectRepository projectRepository;
    private final BoardMapper boardMapper;

    public BoardService(IBoardRepository boardRepository, IProjectRepository projectRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.projectRepository = projectRepository;
        this.boardMapper = boardMapper;
    }

    public List<ListBoardProjection> getBoardsByProjectId(long projectId){
        return this.boardRepository.findByProjectIdOrderBySortIndexAsc(projectId);
    }
    @Transactional
    public void updateBoardOrder(List<BoardSortUpdateRequest> updates) {
        List<Board> boardsToUpdate = new ArrayList<>();

        for (BoardSortUpdateRequest update : updates) {
            Board board = boardRepository.findById(update.id())
                    .orElseThrow(() -> new GlobalException("Board not found", HttpStatus.NOT_FOUND));

            board.setSortIndex(update.sortIndex());
            boardsToUpdate.add(board);
        }

        boardRepository.saveAll(boardsToUpdate);
    }

    public CreateBoardRequest create(CreateBoardRequest request){
        ProjectBasicInfo project = this.projectRepository.findBasicInfoByProjectId(request.getProjectId())
                .orElseThrow(()->new GlobalException("project not found", HttpStatus.NOT_FOUND));
        Integer maxSortIndex = boardRepository.findMaxSortIndexByProjectId(project.getId());
        int newSortIndex = (maxSortIndex == null ? 0 : maxSortIndex + 1);
        Board board = this.boardMapper.toEntity(request);
        board.setSortIndex(newSortIndex);
        board.setProject(this.projectRepository.getReferenceById(project.getId()));
        boardRepository.save(board);
        return request;
    }

}



















