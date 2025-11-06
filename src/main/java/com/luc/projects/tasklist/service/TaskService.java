package com.luc.projects.tasklist.service;

import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;

import java.util.List;

public interface TaskService {
    Task findByIdTask(Long id) throws NoSuchFieldException;
    List<Task> findAllTask();
    Task saveTask(Task newTask);
    void deleteByIdTask(Long id);
    Task updateTask(Long id, Task newTask);

    boolean verificaTaskDuplicada(Task newTask);

    Boolean hasTasks();
    boolean descricaoJaExiste(String descricao);
}
