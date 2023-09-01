package com.StajcellProject.Stajcell.API.Project.service;

import com.StajcellProject.Stajcell.API.Project.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
     void emptyTasksCache();
}
