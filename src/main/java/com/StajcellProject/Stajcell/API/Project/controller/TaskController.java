package com.StajcellProject.Stajcell.API.Project.controller;

import com.StajcellProject.Stajcell.API.Project.model.Task;
import com.StajcellProject.Stajcell.API.Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @PostMapping("/tasks/clear")
    public String clearAllCacheValues(Model model) {
        taskService.clearAllCacheValues("tasksCache");
        return "redirect:/tasks";
    }

}