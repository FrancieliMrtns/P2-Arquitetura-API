package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import application.record.TarefaDTO;
import application.service.TarefaService;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaSrv;

    @GetMapping
    public Iterable<TarefaDTO> findAll(){
        return tarefaSrv.findAll();
    }

    @PostMapping
    public TarefaDTO insert(@RequestBody TarefaDTO tarefa){
        return tarefaSrv.inserirTarefa(tarefa);
    } 





    
}
