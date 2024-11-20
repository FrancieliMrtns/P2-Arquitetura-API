package application.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public Iterable<TarefaDTO> findAll() {
        return tarefaRepo.findAll().stream().map(TarefaDTO::new).toList();
    }

    public TarefaDTO findById(Long id){
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar a tarefa :( ");
        }
        return new TarefaDTO(resultado.get());
    }

    public TarefaDTO insert(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefa.setDataCriacao(LocalDate.now());
        Tarefa insertTarefa = tarefaRepo.save(tarefa);
        return new TarefaDTO(insertTarefa);
    }

    public TarefaDTO update(long id, TarefaDTO tarefaDTO) {
        if (!tarefaRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar a tarefa :( ");
        }
        Tarefa novo = new Tarefa(tarefaDTO);
        novo.setId(id);
        Tarefa atualizado = tarefaRepo.save(novo);
        return new TarefaDTO(atualizado);
    }

    public void delete(long id) {
        if (!tarefaRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar a tarefa :( ");
        }
        tarefaRepo.deleteById(id);
    }

    public void insertColaborador(Long tarefaId, Long colaboradorId) {
        Tarefa tarefa = tarefaRepo.findById(tarefaId).orElse(null);
        if (tarefa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível localizar a tarefa :( ");
        }

        Colaborador colaborador = colaboradorRepo.findById(colaboradorId).orElse(null);
        if (colaborador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível localizar o colaborador :( ");
        }

        tarefa.getColaborador().add(colaborador);
        tarefaRepo.save(tarefa);
    }
    public void deleteColaborador(Long tarefaId, Long colaboradorId) {
        Tarefa tarefa = tarefaRepo.findById(tarefaId).orElse(null);
        if (tarefa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível localizar a tarefa :( ");
        }

        Colaborador colaborador = colaboradorRepo.findById(colaboradorId).orElse(null);
        if (colaborador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível localizar o colaborador :( ");
        }

        tarefa.getColaborador().remove(colaborador);
        tarefaRepo.save(tarefa);

    }
}

