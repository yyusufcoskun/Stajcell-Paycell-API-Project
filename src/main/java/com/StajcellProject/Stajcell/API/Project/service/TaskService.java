package com.StajcellProject.Stajcell.API.Project.service;

import com.StajcellProject.Stajcell.API.Project.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    void emptyTasksCache();
    void clearAllCacheValues(String tasksCache);

    List<Task> getCompletedTasks();
    List<Task> getIncompleteTasks(); 

    List<Task> getTasksByUserIds(List<Long> userIds);//009
    List<Task> getCompletedTasksByUserIds(List<Long> userIds);//009
    List<Task> getIncompleteTasksByUserIds(List<Long> userIds);//009

}
