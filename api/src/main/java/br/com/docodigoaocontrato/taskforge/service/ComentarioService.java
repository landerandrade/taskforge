package br.com.docodigoaocontrato.taskforge.service;

import br.com.docodigoaocontrato.taskforge.dto.ComentarioDTO;
import br.com.docodigoaocontrato.taskforge.dto.TarefaDTO;
import br.com.docodigoaocontrato.taskforge.model.Comentario;
import br.com.docodigoaocontrato.taskforge.model.Tarefa;
import br.com.docodigoaocontrato.taskforge.repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<ComentarioDTO> listarTodos() {
        return comentarioRepository.findAll()
                .stream()
                .map(comentario -> toDto(comentario))
                .toList();
    }

    public Optional<ComentarioDTO> buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .map(comentario -> toDto(comentario));
    }

    public ComentarioDTO criarComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = toEntity(comentarioDTO);
        return toDto(comentarioRepository.save(comentario));
    }

    public Optional<ComentarioDTO> atualizarComentario(Long id, ComentarioDTO comentarioDTO) {
        Optional<Comentario> comentarioRecuperado = comentarioRepository.findById(id);
        if (comentarioRecuperado.isPresent()) {
            Comentario comentario = comentarioRecuperado.get();
            comentario.setDescricao(comentarioDTO.getDescricao());
            comentario.setAutor(comentarioDTO.getAutor());
            return Optional.of(toDto(comentarioRepository.save(comentario)));
        }
        return Optional.empty();
    }

    public boolean deletarComentario(Long id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ComentarioDTO toDto(Comentario comentario) {
        return new ComentarioDTO(comentario.getId(),
                comentario.getDescricao(), comentario.getAutor());
    }

    private Comentario toEntity(ComentarioDTO comentarioDTO) {
        return new Comentario(comentarioDTO.getId(),
                comentarioDTO.getDescricao(), comentarioDTO.getAutor());
    }
}
