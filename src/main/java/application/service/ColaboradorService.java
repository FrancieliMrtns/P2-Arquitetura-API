package application.service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ColaboradorDTO> findAll() {
        return colaboradorRepo.findAll().stream()
            .map(colaborador -> new ColaboradorDTO(colaborador.getId(), colaborador.getNome()))
            .collect(Collectors.toList());
    }

    public ColaboradorDTO insert(ColaboradorDTO colaborador) {
        Colaborador resultado = colaboradorRepo.findByNome(colaborador.nome());
        if (resultado == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Colaborador não existente."
            ); 
        }
        
        Colaborador novoColaborador = new Colaborador(colaborador);
        colaboradorRepo.save(novoColaborador);
        return colaborador;
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaborador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void deleteById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}