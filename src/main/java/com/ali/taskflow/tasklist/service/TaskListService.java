package com.ali.taskflow.tasklist.service;

import com.ali.taskflow.board.entity.Board;
import com.ali.taskflow.board.repository.IBoardRepository;
import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.tasklist.dto.requests.TaskListCreateRequest;
import com.ali.taskflow.tasklist.dto.requests.TaskListSortUpdateRequest;
import com.ali.taskflow.tasklist.dto.requests.TaskListUpdateRequest;
import com.ali.taskflow.tasklist.dto.responses.TaskListResponse;
import com.ali.taskflow.tasklist.entity.TaskList;
import com.ali.taskflow.tasklist.mapper.TaskListMapper;
import com.ali.taskflow.tasklist.repository.ITaskListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final ITaskListRepository taskListRepository;
    private final IBoardRepository boardRepository;
    private final TaskListMapper taskListMapper;

    public TaskListService(ITaskListRepository taskListRepository, IBoardRepository boardRepository, TaskListMapper taskListMapper) {
        this.taskListRepository = taskListRepository;
        this.boardRepository = boardRepository;
        this.taskListMapper = taskListMapper;
    }

    public TaskListResponse createTaskList(TaskListCreateRequest request) {
        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(() -> new GlobalException("Board not found", HttpStatus.NOT_FOUND));

        Integer maxSortIndex = taskListRepository.findMaxSortIndexByBoardId(board.getId());
        int newSortIndex = (maxSortIndex == null) ? 0 : maxSortIndex + 1;

        TaskList taskList = taskListMapper.toEntity(request);
        taskList.setBoard(board);
        taskList.setSortIndex(newSortIndex);

        taskListRepository.save(taskList);
        return taskListMapper.toResponse(taskList);
    }

    public List<TaskListResponse> getTaskListsByBoardId(Long boardId) {
        List<TaskList> lists = taskListRepository.findByBoardIdOrderBySortIndexAsc(boardId);
        return taskListMapper.toResponseList(lists);
    }

    public TaskListResponse updateTaskList(TaskListUpdateRequest request) {
        TaskList taskList = taskListRepository.findById(request.id())
                .orElseThrow(() -> new GlobalException("TaskList not found", HttpStatus.NOT_FOUND));

        taskList.setName(request.name());
        return taskListMapper.toResponse(taskListRepository.save(taskList));
    }

    public void deleteTaskList(Long id) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new GlobalException("TaskList not found", HttpStatus.NOT_FOUND));
        taskListRepository.delete(taskList);
    }

    public void updateTaskListOrder(List<TaskListSortUpdateRequest> updates) {
        for (TaskListSortUpdateRequest update : updates) {
            TaskList taskList = taskListRepository.findById(update.id())
                    .orElseThrow(() -> new GlobalException("TaskList not found", HttpStatus.NOT_FOUND));
            taskList.setSortIndex(update.sortIndex());
            taskListRepository.save(taskList);
        }
    }
}
