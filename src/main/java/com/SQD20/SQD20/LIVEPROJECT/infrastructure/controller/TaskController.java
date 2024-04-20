package com.SQD20.SQD20.LIVEPROJECT.infrastructure.controller;

import com.SQD20.SQD20.LIVEPROJECT.domain.entites.Task;
import com.SQD20.SQD20.LIVEPROJECT.service.TaskService;
import com.SQD20.SQD20.LIVEPROJECT.payload.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController  {

    private final TaskService taskService;

    @PatchMapping("update/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest updateRequest) {
        taskService.updateTask(taskId, updateRequest);
        return ResponseEntity.ok("Task updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return new ResponseEntity<>("Task Deleted Successfully!", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new_task/{userId}/{taskListId}")
    public ResponseEntity<TaskRequest> createTask(@PathVariable Long userId, @PathVariable Long taskListId, @RequestBody TaskRequest createRequest) {
        TaskRequest createdTask = taskService.createTask(userId, taskListId, createRequest);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PatchMapping("/updateStatus/{taskId}")
    public ResponseEntity<String> updateStatus(@PathVariable Long taskId, @RequestBody TaskRequest updateStatus){
        taskService.updateTaskStatus(taskId, updateStatus);
        return ResponseEntity.ok("Task Status updated successfully");
    }
}
