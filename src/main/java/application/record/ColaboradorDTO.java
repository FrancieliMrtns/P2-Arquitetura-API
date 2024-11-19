package application.record;

import application.model.Colaborador;

public record ColaboradorDTO(long id, String nome) {
    public ColaboradorDTO(Colaborador colaboradores) {
        this(colaboradores.getId(), colaboradores.getNome()); 
    }
}