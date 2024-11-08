package application.model;

import java.util.Set;

import application.record.ColaboradorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "colaboradores")
@Getter
@Setter
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "colaboradores_varias_tarefas",
        joinColumns = @JoinColumn(name = "id_colaborador"),
        inverseJoinColumns = @JoinColumn(name = "id_tarefa")
    )
    private Set<Tarefa> tarefas; // Relacionamento correto com Tarefa

    public Colaborador() {}

    public Colaborador(ColaboradorDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
    }
}
