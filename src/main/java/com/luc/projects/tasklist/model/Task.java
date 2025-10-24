package com.luc.projects.tasklist.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_Tarefas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String descricao;

    @Column()
    private LocalDate dataEntrega;

    private Responsavel responsavel;




}
