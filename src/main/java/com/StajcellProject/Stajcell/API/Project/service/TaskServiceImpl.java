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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Cacheable("tasksCache")
    public Task getTasksById(Long id) {
        System.out.println("Getting tasks from API for filtering process...");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .pathSegment(String.valueOf(id));
        ResponseEntity<Task> response = restTemplate.getForEntity(builder.toUriString(), Task.class);

        //ResponseEntity<Task> response = restTemplate.getForEntity(apiUrl + "/" + id, Task.class); Dead code, use in case of emergency
        Task task = response.getBody();
        return task;
    }

    @Cacheable("tasksCache")
    public List<Task> getTasksByFilters(Long userId, Boolean completed) {
        List<Task> allTasks = getAllTasks();

        if (userId != null) {
            allTasks = allTasks.stream()
                    .filter(task -> task.getUserId().equals(userId))
                    .collect(Collectors.toList());
        }

        if (completed != null) {
            allTasks = allTasks.stream()
                    .filter(task -> task.isCompleted() == completed)
                    .collect(Collectors.toList());
        }

        return allTasks;
    }
/*
    @Cacheable("tasksCache")
    public List<Task> getTasksByUser(Long userId) {
        System.out.println("Getting tasks from API for filtering process...");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("userId", userId);
        ResponseEntity<Task[]> response = restTemplate.getForEntity(builder.toUriString(), Task[].class);

        //ResponseEntity<Task[]> response = restTemplate.getForEntity(apiUrl + "?userId=" + userId, Task[].class); Dead code, use in case of emergency
        Task[] tasks = response.getBody();
        return Arrays.asList(tasks);
    }

    @Cacheable("tasksCache")
    public List<Task> getTasksByCompletion(boolean completed) {
        System.out.println("Getting tasks from API for filtering process...");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("completed", completed);
        ResponseEntity<Task[]> response = restTemplate.getForEntity(builder.toUriString(), Task[].class);

        //ResponseEntity<Task[]> response = restTemplate.getForEntity(apiUrl + "?completed=" + completed, Task[].class); Dead code, use in case of emergency
        Task[] tasks = response.getBody();
        return Arrays.asList(tasks);
    }
*/
}

