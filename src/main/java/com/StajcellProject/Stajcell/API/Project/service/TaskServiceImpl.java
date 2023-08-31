package com.StajcellProject.Stajcell.API.Project.service;

import com.StajcellProject.Stajcell.API.Project.model.Task;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
        private final String apiUrl = "https://jsonplaceholder.typicode.com/todos";
        private final RestTemplate restTemplate;

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
    }

