package com.luc.projects.tasklist.service.impl;

import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.repository.TaskRepository;
import com.luc.projects.tasklist.service.ResponsavelService;
import com.luc.projects.tasklist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findByIdTask(Long id) throws NoSuchFieldException {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    @Override
    public void deleteByIdTask(Long id) {
        taskRepository.deleteById(id);
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
