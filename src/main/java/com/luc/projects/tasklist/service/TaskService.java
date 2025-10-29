package com.luc.projects.tasklist.service;

import com.luc.projects.tasklist.model.Task;

import java.util.List;

public interface TaskService {
    Task findByIdTask(Long id) throws NoSuchFieldException;
    List<Task> findAllTask();
    Task createTask(Task newTask);
    void deleteByIdTask(Long id);
    Task updateTask(Long id, Task newTask);
    Boolean hasTasks();
}
