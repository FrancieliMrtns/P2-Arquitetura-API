package application.model;

import java.util.HashSet;
import java.util.Set;

import application.record.ColaboradorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colaboradores")
@Getter
@Setter
@NoArgsConstructor
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "colaborador")

    private Set<Tarefa> tarefas = new HashSet<>();

    public Colaborador(ColaboradorDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
    }
}
