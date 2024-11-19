package application.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.ColaboradorRepository;
import application.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepo;

    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public List<TarefaDTO> findAll() {
        return tarefaRepo.findAll().stream()
            .map(tarefa -> new TarefaDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataCriacao(),
                tarefa.getDataInicio(),
                tarefa.getDataConclusao()
            ))
            .collect(Collectors.toList());
    }

    public TarefaDTO findByIdWithColaboradores(Long id) {
        Tarefa tarefa = tarefaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

        return new TarefaDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getDataCriacao(),
            tarefa.getDataInicio(),
            tarefa.getDataConclusao()

        );}
    @Transactional
    public TarefaDTO insertWithColaboradores(TarefaDTO tarefaDTO) {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tarefaDTO.titulo());
        novaTarefa.setDescricao(tarefaDTO.descricao());
        novaTarefa.setDataCriacao(tarefaDTO.dataCriacao());
        novaTarefa.setDataInicio(tarefaDTO.dataInicio());
        novaTarefa.setDataConclusao(tarefaDTO.dataConclusao());

        Set<Colaborador> colaboradores = tarefaDTO.colaboradores().stream()
            .map(dto -> colaboradorRepo.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado")))
            .collect(Collectors.toSet());

        novaTarefa.setColaboradores(colaboradores);

        Tarefa tarefaSalva = tarefaRepo.save(novaTarefa);

        return new TarefaDTO(
            tarefaSalva.getId(),
            tarefaSalva.getTitulo(),
            tarefaSalva.getDescricao(),
            tarefaSalva.getDataCriacao(),
            tarefaSalva.getDataInicio(),
            tarefaSalva.getDataConclusao()
        );
    }

    @Transactional
    public TarefaDTO updateWithColaboradores(Long id, TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setDataInicio(tarefaDTO.dataInicio());
        tarefa.setDataConclusao(tarefaDTO.dataConclusao());

        Set<Colaborador> colaboradores = tarefaDTO.colaboradores().stream()
        .map(dto -> colaboradorRepo.findById(dto.id())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado")))
        .collect(Collectors.toSet()); // Corrigido: removido o ponto e vírgula no meio da expressão

        Tarefa tarefaAtualizada = tarefaRepo.save(tarefa);

        return new TarefaDTO(
            tarefaAtualizada.getId(),
            tarefaAtualizada.getTitulo(),
            tarefaAtualizada.getDescricao(),
            tarefaAtualizada.getDataCriacao(),
            tarefaAtualizada.getDataInicio(),
            tarefaAtualizada.getDataConclusao()
        );
    }
}

