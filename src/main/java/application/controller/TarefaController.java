package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.TarefaDTO;
import application.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    TarefaService tarefaService;

    @GetMapping
    public Iterable<TarefaDTO> findAll(){
        return tarefaService.findAll();
    }


    
}
