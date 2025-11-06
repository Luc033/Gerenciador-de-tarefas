package com.luc.projects.tasklist.service.impl;

import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.repository.TaskRepository;
import com.luc.projects.tasklist.service.TaskService;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

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
        if (Objects.isNull(newTask)) {
            throw new NullPointerException();
        }

        newTask.setDescricao(newTask.getDescricao().trim());
        if(!verificaTaskDuplicada(newTask)){
            return taskRepository.save(newTask);
        }else{
            throw new IllegalStateException("Não foi possível salvar.");
        }

    }

    @Override
    public void deleteByIdTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        return null;
    }

    public boolean descricaoJaExiste(String descricao) {
        return taskRepository.existsByDescricao(descricao);
    }

    @Override
    public boolean verificaTaskDuplicada(Task newTask) {
        if (taskRepository.existsByDescricao(newTask.getDescricao())) {
            Task t = null;
            try{
                t = taskRepository.findByDescricao(newTask.getDescricao());
            }catch (NonUniqueResultException | IncorrectResultSizeDataAccessException ex ){
                System.err.println("Erro ao salvar Task: Existe mais um valor com a mesma descrição [ " + ex + "].");
            }
            System.out.println("[OBJETO RETORNADO] "+ t);
            if (t != null) {
                if (newTask.getId() == t.getId()) {
                    return false;
                }else{
                    return true;
                }
            }else { throw new NullPointerException("Objeto retornado é nulo: " + t ); }
        }

        return false;
    }

    @Override
    public Boolean hasTasks() {
        var taskList = findAllTask();
        if (taskList.isEmpty()) {
            return false;
        } else {
            return false;
        }
    }
}
