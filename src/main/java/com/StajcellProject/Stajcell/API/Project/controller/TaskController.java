package com.StajcellProject.Stajcell.API.Project.controller;

import com.StajcellProject.Stajcell.API.Project.model.Task;
import com.StajcellProject.Stajcell.API.Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/clear")
    public void clearAllCacheValues(String tasksCache) {
        taskService.clearAllCacheValues("tasksCache");
    }

    @GetMapping("/api/tasks/{id}")
    @ResponseBody
    public Task getTasksById(@PathVariable Long id) {
        return taskService.getTasksById(id);
    }

    @GetMapping("/api/tasks")
    @ResponseBody
    public ResponseEntity<List<Task>> getTasksByFilters(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean completed) {

        List<Task> tasks = taskService.getTasksByFilters(userId, completed);
        return ResponseEntity.ok(tasks);
    }


/*
    @GetMapping("/tasks")
    public void listTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }
*//*
    @GetMapping("/api/tasks")
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

*//*
    @GetMapping("/api/tasks")
    @ResponseBody
    public ResponseEntity<List<Task>> getTasksByFilters(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean completed
    ) {
        List<Task> tasks = taskService.getAllTasks();

        if (userId != null) {
            tasks = tasks.stream().filter(task -> task.getUserId().equals(userId)).collect(Collectors.toList());
        }
        if (completed != null) {
            tasks = tasks.stream().filter(task -> task.isCompleted() == completed).collect(Collectors.toList());
        }
        return ResponseEntity.ok(tasks);
    }
*//*
    @GetMapping("/api/tasks/users/{userId}")
    @ResponseBody
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUser(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/api/tasks/completed/{completed}")
    @ResponseBody
    public ResponseEntity<List<Task>> getTasksByCompletion(@PathVariable boolean completed) {
        List<Task> tasks = taskService.getTasksByCompletion(completed);
        return ResponseEntity.ok(tasks);
    }
*/
}