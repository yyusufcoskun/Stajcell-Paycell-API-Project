package com.StajcellProject.Stajcell.API.Project.service;

import com.StajcellProject.Stajcell.API.Project.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Service
public class TaskServiceImpl implements TaskService {
    private final String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    private final RestTemplate restTemplate;

    @Autowired
    CacheManager cacheManager;

    public TaskServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cacheable("tasksCache")
    public List<Task> getAllTasks() {
        System.out.println("Getting tasks from API...");
        ResponseEntity<Task[]> response = restTemplate.getForEntity(apiUrl, Task[].class);
        Task[] tasks = response.getBody();
        return Arrays.asList(tasks);
    }

    @CacheEvict(value = "tasksCache", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.taskTTL}")
    public void emptyTasksCache() {
        System.out.println("Emptying cache...");
    }

    public void clearAllCacheValues(String tasksCache) {
        System.out.println("Emptying cache manually...");
        cacheManager.getCache(tasksCache).clear();
    }

    public List<Task> getCompletedTasks() {
        List<Task> allTasks = getAllTasks();
        List<Task> completedTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }

        return completedTasks;
    }

    @Override
    public List<Task> getIncompleteTasks() {
        List<Task> allTasks = getAllTasks();
        List<Task> incompleteTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (!task.isCompleted()) {
                incompleteTasks.add(task);
            }
        }

        return incompleteTasks;
    }

    public List<Task> getTasksByUserIds(List<Long> userIds) {
        List<Task> allTasks = getAllTasks();
        List<Task> filteredTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (userIds.contains(task.getUserId())) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    @Override
    public List<Task> getCompletedTasksByUserIds(List<Long> userIds) {
        List<Task> allTasks = getAllTasks();
        List<Task> completedTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (userIds.contains(task.getUserId()) && task.isCompleted()) {
                completedTasks.add(task);
            }
        }

        return completedTasks;
    }

    @Override
    public List<Task> getIncompleteTasksByUserIds(List<Long> userIds) {
        List<Task> allTasks = getAllTasks();
        List<Task> incompleteTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (userIds.contains(task.getUserId()) && !task.isCompleted()) {
                incompleteTasks.add(task);
            }
        }

        return incompleteTasks;
    }
}
