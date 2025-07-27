package com.ali.taskflow.taskassignee.service;


import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.task.entity.Task;
import com.ali.taskflow.taskassignee.dto.requests.CreateTaskAssigneeRequest;
import com.ali.taskflow.taskassignee.dto.responses.TaskAssigneeResponse;
import com.ali.taskflow.taskassignee.entity.TaskAssignee;
import com.ali.taskflow.taskassignee.mapper.TaskAssigneeMapper;
import com.ali.taskflow.taskassignee.repository.ITaskAssigneeRepository;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskAssigneeService {

    private final ITaskAssigneeRepository taskAssigneeRepository;
    private final ITaskAssigneeRepository taskRepository;
    private final IUserRepository userRepository;
    private final TaskAssigneeMapper taskAssigneeMapper;

    public TaskAssigneeService(ITaskAssigneeRepository taskAssigneeRepository, ITaskAssigneeRepository taskRepository, IUserRepository userRepository, TaskAssigneeMapper taskAssigneeMapper) {
        this.taskAssigneeRepository = taskAssigneeRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskAssigneeMapper = taskAssigneeMapper;
    }

    @Transactional
    public TaskAssigneeResponse assignTaskToUser(CreateTaskAssigneeRequest request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(()-> new GlobalException("Task not found",HttpStatus.NOT_FOUND)).getTask();
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GlobalException("User not found" , HttpStatus.NOT_FOUND));

        if (taskAssigneeRepository.findByTaskIdAndUserId(task.getId(), user.getId()).isPresent()) {
            throw new GlobalException("Task is already assigned to this user.",HttpStatus.NOT_FOUND);
        }

        TaskAssignee taskAssignee = new TaskAssignee(task, user);
        TaskAssignee savedTaskAssignee = taskAssigneeRepository.save(taskAssignee);
        return taskAssigneeMapper.toResponse(savedTaskAssignee);
    }

    @Transactional
    public List<TaskAssigneeResponse> getAssigneesForTask(Long taskId) {
        List<TaskAssignee> assignees = taskAssigneeRepository.findByTaskId(taskId);
        return assignees.stream()
                .map(taskAssigneeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void unassignTaskFromUser(Long taskId, Long userId) {
        TaskAssignee taskAssignee = taskAssigneeRepository.findByTaskIdAndUserId(taskId, userId)
                .orElseThrow(() -> new GlobalException("Assignment not found: ",HttpStatus.NOT_FOUND));
        taskAssigneeRepository.delete(taskAssignee);
    }
}
