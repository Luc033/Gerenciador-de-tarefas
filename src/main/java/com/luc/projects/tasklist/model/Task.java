package com.luc.projects.tasklist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_Tarefas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @NotEmpty(message = "A descrição não pode estar vaziao.")
    @Size(min = 4, message = "A descrição deve ter no mínimo 3 caracteres.")
    @Column(unique = true, nullable = false)
    private String descricao;

    @NotNull(message = "Data precisa ser preechida.")
    @FutureOrPresent(message = "Data inválida. Não são permitidas datas passadas.")
    @Column()
    private LocalDate dataEntrega;

    @Column(name = "concluido")
    private Boolean concluido;

    @NotNull(message = "O responsável não pode estar vazio.")
    @ManyToOne()
    @JoinColumn(name = "responsavel_id_fk")
    private Responsavel responsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }


    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    // Atualizar o atributo 'concluido' para 'true'
    public void isConcluido(){
        setConcluido(true);
    }

    // Em Task.java
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;

        // Apenas a chave de negócio (unique) importa.
        return Objects.equals(getDescricao(), task.getDescricao());
    }

    @Override
    public int hashCode() {
        // Apenas a chave de negócio (unique) importa.
        return Objects.hashCode(getDescricao());
    }

    @Override
    public String toString() {
        return "Task{" +
                "concluido=" + concluido +
                ", dataEntrega=" + dataEntrega +
                ", descricao='" + descricao + '\'' +
                ", id=" + id +
                '}';
    }
}
