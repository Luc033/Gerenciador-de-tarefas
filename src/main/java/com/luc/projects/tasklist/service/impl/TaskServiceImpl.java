package com.luc.projects.tasklist.service.impl;

import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.repository.TaskRepository;
import com.luc.projects.tasklist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findByIdTask(Long id) throws NoSuchFieldException {
        return taskRepository.findById(id).orElseThrow(NoSuchFieldException::new);
    }

    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task newTask) {
        return null;
    }

    @Override
    public void deleteByIdTask(Long id) {

    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        return null;
    }

    @Override
    public Boolean hasTasks() {
        var taskList = findAllTask();
        if(taskList.isEmpty()){
            return  false;
        }else {
            return false;
        }
    }
}
