package com.luc.projects.tasklist.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_responsavel")
    private String nome;

    @OneToMany(mappedBy="responsavel",
            cascade = CascadeType.ALL, // Salva/Atualiza/Deleta as Tasks junto com o Responsavel
            orphanRemoval = true       // Remove Tasks do banco se elas forem removidas da 'taskList'
    )
    private List<Task> taskList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "id=" + id +
                ", nome='" + nome +
                '}';
    }

    // Em Responsavel.java
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        // getClass() != o.getClass() é mais seguro que 'instanceof' com proxies do Hibernate
        if (o == null || getClass() != o.getClass()) return false;

        Responsavel that = (Responsavel) o;

        // Se o ID for nulo (não persistido), só é igual a si mesmo.
        // Se o ID não for nulo, compara os IDs.
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        // Se o ID for nulo, usa um hash padrão.
        // Se não for nulo, usa o hash do ID.
        // Usar getClass().hashCode() é uma prática comum para entidades baseadas em ID.
        return getClass().hashCode();
    }
}
