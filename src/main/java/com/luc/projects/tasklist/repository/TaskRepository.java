package com.luc.projects.tasklist.repository;

import com.luc.projects.tasklist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Retorna 'true' ou 'false' verificando se uma tarefa existe pela descrição
    boolean existsByDescricao(String descricao);

    // Retorna uma tarefa a partir de uma descrição informada
    Task findByDescricao(String descricao);

}
