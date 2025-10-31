package com.luc.projects.tasklist.service.impl;


import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.repository.ResponsavelRepository;
import com.luc.projects.tasklist.service.ResponsavelService;
import com.luc.projects.tasklist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResponsavelServiceImpl implements ResponsavelService {
    ResponsavelRepository responsavelRepository;

    public ResponsavelServiceImpl(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public List<Responsavel> findAllResponsavel() {

        return responsavelRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Responsavel createResponsavel(Responsavel newResponsavel) {
        if (newResponsavel.getId() == null) {
            return responsavelRepository.save(newResponsavel);
        } else if (responsavelRepository.existsByNome(newResponsavel.getNome())) {
        }
        throw new IllegalArgumentException(String.format("Responsável %s já existe.", newResponsavel.getNome()));
    }


    @Override
    public Responsavel findByIdResponsavel(Long id) {
        return null;
    }

    @Override
    public void deleteByIdResponsavel(Long id) {

    }

    @Override
    public Responsavel updateResponsavel(Responsavel newResponsavel) {
        return null;
    }
}
