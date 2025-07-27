package com.ali.taskflow.task.service;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.task.dto.requests.TaskCreateRequest;
import com.ali.taskflow.task.dto.requests.TaskUpdateRequest;
import com.ali.taskflow.task.dto.responses.TaskResponse;
import com.ali.taskflow.task.entity.Task;
import com.ali.taskflow.task.mapper.TaskMapper;
import com.ali.taskflow.task.repository.ITaskRepository;
import com.ali.taskflow.tasklist.entity.TaskList;
import com.ali.taskflow.tasklist.repository.ITaskListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final ITaskRepository taskRepository;
    private final ITaskListRepository taskListRepository;
    private final TaskMapper taskMapper;

    public TaskService(ITaskRepository taskRepository, ITaskListRepository taskListRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public TaskResponse create(TaskCreateRequest request) {
        TaskList taskList = taskListRepository.findById(request.taskListId())
                .orElseThrow(() -> new GlobalException("TaskList not found",HttpStatus.NOT_FOUND));

        int maxSortIndex = taskRepository.findMaxSortIndexByTaskListId(taskList.getId()).orElse(0);

        Task task = taskMapper.toEntity(request);
        task.setTaskList(taskList);
        task.setSortIndex(maxSortIndex + 1);

        taskRepository.save(task);

        return taskMapper.toResponse(task);
    }

    public List<TaskResponse> getAllByTaskListId(Long taskListId) {
        return taskRepository.findAllByTaskListIdOrderBySortIndex(taskListId)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse update(TaskUpdateRequest request) {
        Task task = taskRepository.findById(request.id())
                .orElseThrow(() -> new GlobalException("Task not found", HttpStatus.NOT_FOUND));

        task.setTitle(request.title());
        task.setDescription(request.description());

        return taskMapper.toResponse(task);
    }

    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
