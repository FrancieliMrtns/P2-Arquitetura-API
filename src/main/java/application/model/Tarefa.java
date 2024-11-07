package application.model;

import java.time.LocalDate;

import application.record.TarefaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    @Column(nullable = false)
    private LocalDate dataCriacao;
    private LocalDate dataInicio;
    private LocalDate dataConclusao; 

    public Tarefa(TarefaDTO dados){
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.dataCriacao = dados.dataCriacao();
        this.dataInicio = dados.dataInicio();
        this.dataConclusao = dados.dataConclusao();

    }
}
