package com.StajcellProject.Stajcell.API.Project.service;

import com.StajcellProject.Stajcell.API.Project.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    void emptyTasksCache();
    void clearAllCacheValues(String tasksCache);
    Task getTasksById(Long id);
    List<Task> getTasksByFilters(Long userId, Boolean completed);

    /*
    List<Task> getTasksByUser(Long userId);
    List<Task> getTasksByCompletion(boolean completed);
     */
}
