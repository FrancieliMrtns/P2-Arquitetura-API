package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.ColaboradorDTO;
import application.service.ColaboradorService;

@RestController
@RequestMapping("colaborador")
public class ColaboradoresController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public Iterable<ColaboradorDTO> findAll() {
        return colaboradorService.findAll();
    }

    @GetMapping("/{id}")
    public ColaboradorDTO findById(@PathVariable long id) {
        return colaboradorService.findById(id);
    }

    @PostMapping
    public ColaboradorDTO insert(@RequestBody ColaboradorDTO colaboradorDTO) {
        return colaboradorService.insert(colaboradorDTO);
    }

    @PutMapping("/{id}")
    public ColaboradorDTO update(
            @PathVariable long id,
            @RequestBody ColaboradorDTO colaboradorDTO) {
        return colaboradorService.update(id, colaboradorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        colaboradorService.delete(id);
    }
}