package br.com.docodigoaocontrato.taskforge.controller;

import br.com.docodigoaocontrato.taskforge.dto.ComentarioDTO;
import br.com.docodigoaocontrato.taskforge.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> listarTodos() {
        return ResponseEntity.ok(comentarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> buscarPorId(@PathVariable Long id) {
        Optional<ComentarioDTO> comentarioDTO = comentarioService.buscarPorId(id);
        if (comentarioDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(comentarioDTO.get());
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> criar(@RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comentarioService.criarComentario(comentarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO){
        Optional<ComentarioDTO> comentarioAtualizado = comentarioService.atualizarComentario(id, comentarioDTO);
        if (comentarioAtualizado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(comentarioAtualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (comentarioService.deletarComentario(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
