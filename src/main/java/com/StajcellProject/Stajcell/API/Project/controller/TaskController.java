package com.StajcellProject.Stajcell.API.Project.controller;

import com.StajcellProject.Stajcell.API.Project.model.Task;
import com.StajcellProject.Stajcell.API.Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; 

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public String listTasks(
    Model model,
    @RequestParam(name = "filter", required = false, defaultValue = "all") String filterCriteria,
    @RequestParam(name = "userIds", required = false) String userIds
) {
    List<Task> tasks;
    List<Long> userIdList = null; // userIdList'i tanımlıyoruz

    if (userIds != null && !userIds.isEmpty()) {
        // Parse User IDs from the input string
        userIdList = Arrays.stream(userIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    if ("completed".equals(filterCriteria) && (userIdList == null || userIdList.isEmpty())) {
        // Filter tasks to show only completed tasks
        tasks = taskService.getCompletedTasks();
    } else if ("incomplete".equals(filterCriteria) && (userIdList == null || userIdList.isEmpty())) {
        // Filter tasks to show only incomplete tasks
        tasks = taskService.getIncompleteTasks();
    } else if ("completed".equals(filterCriteria) && userIdList != null && !userIdList.isEmpty()) {
        // Filter completed tasks by User IDs
        tasks = taskService.getCompletedTasksByUserIds(userIdList);
    } else if ("incomplete".equals(filterCriteria) && userIdList != null && !userIdList.isEmpty()) {
        // Filter incomplete tasks by User IDs
        tasks = taskService.getIncompleteTasksByUserIds(userIdList);
    } else if (userIdList != null && !userIdList.isEmpty()) {
        // Default: Show tasks for specified User IDs
        tasks = taskService.getTasksByUserIds(userIdList);
    } else {
        // Default: Show all tasks
        tasks = taskService.getAllTasks();
    }

    model.addAttribute("tasks", tasks);
    return "task-list";
}

    
    @PostMapping("tasks/clear")
    public String clearAllCacheValues(Model model) {
        taskService.clearAllCacheValues("tasksCache");
        return "redirect:/";
    }

}