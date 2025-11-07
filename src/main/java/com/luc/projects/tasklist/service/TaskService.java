package com.luc.projects.tasklist.service;

import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;

import java.util.List;

public interface TaskService {
    // Procura e retorna um tarefa pelo ID informado
    Task findByIdTask(Long id) throws NoSuchFieldException;

    // Retorna todas tarefas do banco de dados em lista
    List<Task> findAllTask();

    // Salva uma tarefa informada em seu parâmetro
    Task saveTask(Task newTask);

    // Deleta uma tarefa pelo ID informado
    void deleteByIdTask(Long id);

    // Retorna 'true' ou 'false' verificando se uma tarefa já existe informando no parâmetro o objeto novo Task
    boolean verificaTaskDuplicada(Task newTask);

    // Retorna 'true' ou 'false' verificando se uma tarefa já existe informando no parâmetro a sua descrição
    boolean descricaoJaExiste(String descricao);
}
