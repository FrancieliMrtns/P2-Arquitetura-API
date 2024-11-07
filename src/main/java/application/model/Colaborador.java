package application.model;

import application.record.ColaboradorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


    public Colaborador(ColaboradorDTO dados){
        this.id = dados.id();
        this.nome = dados.nome();
    }
    
}
