package br.com.docodigoaocontrato.taskforge.controller;

import br.com.docodigoaocontrato.taskforge.model.Tarefa;
import br.com.docodigoaocontrato.taskforge.service.TarefaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public List<Tarefa> listar() {
        return tarefaService.buscarTodos();
    }
}
