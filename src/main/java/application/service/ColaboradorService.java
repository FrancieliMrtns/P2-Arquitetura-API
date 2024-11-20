package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Colaborador;
import application.record.ColaboradorDTO;
import application.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public Iterable<ColaboradorDTO> findAll() {
        return colaboradorRepo.findAll().stream()
            .map(ColaboradorDTO::new).toList();
    }
    public ColaboradorDTO findById(long id) {
        Optional<Colaborador> resultado = colaboradorRepo.findById(id);
        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar o colaborador :( ");
        }
        return new ColaboradorDTO(resultado.get());
    }

    public ColaboradorDTO insert(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador(colaboradorDTO);
        Colaborador insertColaborador = colaboradorRepo.save(colaborador);
        return new ColaboradorDTO(insertColaborador);
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaboradorDTO) {
        if (!colaboradorRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar o colaborador :( ");
        }
        Colaborador novo = new Colaborador(colaboradorDTO);
        novo.setId(id);
        Colaborador atualizado = colaboradorRepo.save(novo);
        return new ColaboradorDTO(atualizado);
    }

    public void delete(long id) {
        if (!colaboradorRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não foi possível localizar o colaborador :( ");
        }
        colaboradorRepo.deleteById(id);
    }
}