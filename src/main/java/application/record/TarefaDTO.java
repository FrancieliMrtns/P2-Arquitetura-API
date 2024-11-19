package application.record;

import application.model.Tarefa;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


public record TarefaDTO(long id, String titulo, String descricao, LocalDate dataCriacao, LocalDate dataInicio, LocalDate dataConclusao, Set<ColaboradorDTO> colaboradores) {
    public TarefaDTO(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataCriacao(),
                tarefa.getDataInicio(),
                tarefa.getDataConclusao(),
                tarefa.getColaborador().stream().map(ColaboradorDTO::new).collect(Collectors.toSet()));
    }
}