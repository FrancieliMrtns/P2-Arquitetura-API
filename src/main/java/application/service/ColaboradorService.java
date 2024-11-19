package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.record.ColaboradorDTO;
import application.record.TarefaDTO;
import application.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
    @Autowired

    public ColaboradorDTO insert(ColaboradorDTO colaborador) {
        Colaborador resultado = colaboradorRepo.findByNome(colaborador.nome());
        if (resultado != null) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Colaborador já existente."
            );
        }

        Colaborador novoColaborador = new Colaborador(colaborador);
        colaboradorRepo.save(novoColaborador);
        return colaborador;
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaborador) {
        Colaborador existingColaborador = colaboradorRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        existingColaborador.setNome(colaborador.nome());
        colaboradorRepo.save(existingColaborador);
        return new ColaboradorDTO(existingColaborador.getId(), existingColaborador.getNome());
    }

    public void deleteById(long id) {
        if (!colaboradorRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Colaborador não encontrado");
        }

        colaboradorRepo.deleteById(id);
    }
}
