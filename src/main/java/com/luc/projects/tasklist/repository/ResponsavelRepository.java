package com.luc.projects.tasklist.repository;

import com.luc.projects.tasklist.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    // Retorna 'true' ou 'false' verificando se um responsável existe pelo nome
    boolean existsByNome(String nome);
}
