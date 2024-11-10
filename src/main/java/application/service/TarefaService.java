package application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.TarefaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepo;

    public List<TarefaDTO> findAll(){
        return tarefaRepo.findAll().stream()
            .map(tarefa -> new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao(), tarefa.getDataInicio(), tarefa.getDataConclusao()))
            .collect(Collectors.toList());
    }
    @Transactional
public TarefaDTO atualizarTarefa(Long id, TarefaDTO novosDados) {
    Optional<Tarefa> tarefaExistenteOpt = tarefaRepo.findById(id);
    
    if (tarefaExistenteOpt.isPresent()) {
        Tarefa tarefaExistente = tarefaExistenteOpt.get();
        tarefaExistente.setTitulo(novosDados.titulo());
        tarefaExistente.setDescricao(novosDados.descricao());
        tarefaExistente.setDataInicio(novosDados.dataInicio());
        tarefaExistente.setDataConclusao(novosDados.dataConclusao());

        Tarefa tarefaAtualizada = tarefaRepo.save(tarefaExistente);
        
        return new TarefaDTO(
            tarefaAtualizada.getId(),
            tarefaAtualizada.getTitulo(),
            tarefaAtualizada.getDescricao(),
            tarefaAtualizada.getDataCriacao(),
            tarefaAtualizada.getDataInicio(),
            tarefaAtualizada.getDataConclusao()
        );
    } else {
        throw new RuntimeException("Tarefa não encontrada com o ID: " + id);
    }
}



}
    
    
   

