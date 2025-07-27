package com.ali.taskflow.taskComment.service;

import com.ali.taskflow.shared.exception.globalException.GlobalException;
import com.ali.taskflow.task.entity.Task;
import com.ali.taskflow.task.repository.ITaskRepository;
import com.ali.taskflow.taskComment.dto.requests.CreateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.requests.UpdateTaskCommentRequest;
import com.ali.taskflow.taskComment.dto.responses.TaskCommentResponse;
import com.ali.taskflow.taskComment.entity.TaskComment;
import com.ali.taskflow.taskComment.mapper.TaskCommentMapper;
import com.ali.taskflow.taskComment.repository.ITaskCommentRepository;
import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskCommentService {
    private final ITaskCommentRepository taskCommentRepository;
    private final ITaskRepository taskRepository;
    private final IUserRepository userRepository;
    private final TaskCommentMapper taskCommentMapper;

    public TaskCommentService(ITaskCommentRepository taskCommentRepository, ITaskRepository taskRepository, IUserRepository userRepository, TaskCommentMapper taskCommentMapper) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskCommentMapper = taskCommentMapper;
    }

    @Transactional
    public TaskCommentResponse createComment(CreateTaskCommentRequest request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new GlobalException("Task not found with ID: ", HttpStatus.NOT_FOUND));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GlobalException("User not found with ID: ",HttpStatus.NOT_FOUND));

        TaskComment taskComment = new TaskComment(task, user, request.getContent());
        TaskComment savedTaskComment = taskCommentRepository.save(taskComment);
        return taskCommentMapper.toResponse(savedTaskComment);
    }

    @Transactional
    public List<TaskCommentResponse> getCommentsForTask(Long taskId) {
        List<TaskComment> comments = taskCommentRepository.findByTaskIdOrderByCreatedAtAsc(taskId);
        return comments.stream()
                .map(taskCommentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskCommentResponse getCommentById(Long id) {
        TaskComment comment = taskCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task Comment not found with ID: " + id));
        return taskCommentMapper.toResponse(comment);
    }

    @Transactional
    public TaskCommentResponse updateComment(Long id, UpdateTaskCommentRequest request) {
        TaskComment existingComment = taskCommentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Task Comment not found with ID: ",HttpStatus.NOT_FOUND));

        // You might want to add authorization here to ensure only the creator or an admin can update
        taskCommentMapper.updateTaskCommentFromDto(request, existingComment);
        TaskComment updatedComment = taskCommentRepository.save(existingComment);
        return taskCommentMapper.toResponse(updatedComment);
    }

    @Transactional
    public void deleteComment(Long id) {
        if (!taskCommentRepository.existsById(id)) {
            throw new GlobalException("Task Comment not found with ID: ",HttpStatus.NOT_FOUND);
        }
        taskCommentRepository.deleteById(id);
    }
}
