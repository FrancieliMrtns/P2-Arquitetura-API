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
@RequestMapping("/colaboradores")
public class ColaboradoresController {

    @Autowired
    private ColaboradorService colaboradorSrv;

    @PostMapping
    public ColaboradorDTO insert(@RequestBody ColaboradorDTO colaborador) {
        return colaboradorSrv.insert(colaborador);
    }

    @GetMapping
    public Iterable<ColaboradorDTO> list() {
        return colaboradorSrv.findAll();
    }

    @GetMapping("/{id}")
    public ColaboradorDTO findOne(@PathVariable long id) {
        return colaboradorSrv.findById(id);
    }

    @PutMapping("/{id}")
    public ColaboradorDTO update(@PathVariable long id, @RequestBody ColaboradorDTO colaborador) {
        return colaboradorSrv.update(id, colaborador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        colaboradorSrv.deleteById(id);
    }
}
