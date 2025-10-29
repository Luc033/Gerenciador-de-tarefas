package com.luc.projects.tasklist.service;

import com.luc.projects.tasklist.model.Responsavel;

import java.util.List;

public interface ResponsavelService {
    Responsavel createResponsavel(Responsavel newResponsavel);
    Responsavel findByIdResponsavel(Long id);
    void deleteByIdResponsavel(Long id);
    Responsavel updateResponsavel(Responsavel newResponsavel);
    List<Responsavel> findAllResponsavel();
}
