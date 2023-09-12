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

/*
    @GetMapping("/tasks")
    public void listTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }
*/

    @GetMapping("/api/tasks")
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/api/clear")
    public void clearAllCacheValues(Model model) {
        taskService.clearAllCacheValues("tasksCache");
       // return "redirect:/";
    }

    @GetMapping("/api/tasks/{id}")
    @ResponseBody
    public Task getTasksById(@PathVariable Long id) {
        return taskService.getTasksById(id);
    }

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
}