package com.luc.projects.tasklist.service;

import com.luc.projects.tasklist.model.Responsavel;

import java.util.List;

public interface ResponsavelService {
    //Cria um novo responsável
    Responsavel createResponsavel(Responsavel newResponsavel);

    // Procura pelo ID informado e retorna um responsável
    Responsavel findByIdResponsavel(Long id);

    // Deleta por ID um responsável
    void deleteByIdResponsavel(Long id);

    // Atualiza as informações de um responsável informando no parâmetro o novo responsável
    Responsavel updateResponsavel(Responsavel newResponsavel);

    // Procura todos os responsáveis salvo no banco de dados e retorna o resultado em uma lista
    List<Responsavel> findAllResponsavel();
}
