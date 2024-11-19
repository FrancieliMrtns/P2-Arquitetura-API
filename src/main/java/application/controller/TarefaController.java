package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.TarefaDTO;
import application.service.TarefaService;



@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaSrv;

    @PostMapping
    public TarefaDTO insert(@RequestBody TarefaDTO tarefa) {
        return tarefaSrv.insertWithColaboradores(tarefa); // Adiciona vínculo de colaboradores.
    }

    @GetMapping
    public Iterable<TarefaDTO> findAll() {
        return tarefaSrv.findAll();
    }


    /*     @GetMapping("/{id}")
    public TarefaDTO findOne(@PathVariable long id) {
        return tarefaSrv.findByIdWithColaboradores(id); // Inclui detalhes dos colaboradores.
    }

    @PutMapping("/{id}")
    public TarefaDTO update(@PathVariable long id, @RequestBody TarefaDTO tarefa) {
        return tarefaSrv.updateWithColaboradores(id, tarefa); // Atualiza com vínculos.
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        tarefaSrv.deleteById(id);
    }*/

}


