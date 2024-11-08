package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.record.TarefaDTO;
import application.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepo;

    public List<TarefaDTO> findAll(){
        return tarefaRepo.findAll().stream()
            .map(tarefa -> new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao(), tarefa.getDataInicio(), tarefa.getDataConclusao()))
            .collect(Collectors.toList());
    }

    
}
