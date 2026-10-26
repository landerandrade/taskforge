package br.com.docodigoaocontrato.taskforge.service;

import br.com.docodigoaocontrato.taskforge.dto.TarefaDTO;
import br.com.docodigoaocontrato.taskforge.model.Tarefa;
import br.com.docodigoaocontrato.taskforge.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void deveDevolverUmaTarefaQuandoOIdExiste() {
        // DADO que o banco "tem" a tarefa 1
        Tarefa tarefa = new Tarefa(1L, "Estudar", 1, false);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        // QUANDO eu busco a tarefa
        Optional<TarefaDTO> resultado = tarefaService.buscarTarefaPorId(1L);

        // ENTAO a tarefa é recuperada e convertida em DTO
        assertTrue(resultado.isPresent());
        assertEquals("Estudar", resultado.get().getNome());
        assertEquals(1, resultado.get().getPrioridade());
    }

    @Test
    void deveDevolverVazioQuandoOIdNaoExiste(){
        // DADO que o banco nao "tem" a tarefa 99
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        // QUANDO eu busco a tarefa 99
        Optional<TarefaDTO> resultado = tarefaService.buscarTarefaPorId(99L);

        // ENTAO nao vem nenhum resultado
        assertTrue(resultado.isEmpty());
    }

    @Test
    void naoDeveApagarQuandoATarefaNaoExiste() {
        // DADO que o banco nao "tem" a tarefa 99
        when(tarefaRepository.existsById(99L)).thenReturn(false);

        // QUANDO eu tento apagar
        boolean isApagada = tarefaService.deletarTarefa(99L);

        // ENTAO a service retorna false
        assertFalse(isApagada);
        verify(tarefaRepository, never()).deleteById(99L);
        verify(tarefaRepository, only()).existsById(99L);
    }
}
