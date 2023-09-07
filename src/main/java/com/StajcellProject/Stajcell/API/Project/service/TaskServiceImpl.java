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
        private final String apiUrl = "https://jsonplaceholder.typicode.com/todos"; // Spring boot config server client. kodun içinden url'i kaldir.
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

         // Existing code...

    public List<Task> getCompletedTasks() {
        List<Task> allTasks = getAllTasks();
        List<Task> completedTasks = new ArrayList<>();

        // Filter the completed tasks from all tasks
        for (Task task : allTasks) {
            if (task.isCompleted()) {  // Assuming you have a method to check completion status
                completedTasks.add(task);
            }
        }

        return completedTasks;
    }
    //** */
    @Override
        public List<Task> getIncompleteTasks() {
            return null;
        } 
}

