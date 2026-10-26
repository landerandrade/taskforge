package br.com.docodigoaocontrato.taskforge.service;

import br.com.docodigoaocontrato.taskforge.dto.LoginDTO;
import br.com.docodigoaocontrato.taskforge.dto.TokenDTO;
import br.com.docodigoaocontrato.taskforge.dto.UsuarioCadastroDTO;
import br.com.docodigoaocontrato.taskforge.dto.UsuarioDTO;
import br.com.docodigoaocontrato.taskforge.model.Usuario;
import br.com.docodigoaocontrato.taskforge.repository.UsuarioRepository;
import br.com.docodigoaocontrato.taskforge.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtService jwtService = new JwtService();


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<UsuarioDTO> cadastrar(UsuarioCadastroDTO dto) {
        if (usuarioRepository.existsUsuarioByEmail(dto.getEmail())) {
            return Optional.empty();
        }
        String senhaCriptografada = encoder.encode(dto.getSenha());
        Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), senhaCriptografada);
        return Optional.of(toDto(usuarioRepository.save(usuario)));
    }

    public Optional<TokenDTO> login(LoginDTO dto) {
        Optional<Usuario> encontrado = usuarioRepository.findByEmail(dto.getEmail());
        if (encontrado.isEmpty()) {
            return Optional.empty();
        }
        Usuario usuario = encontrado.get();
        // lander123 -> criptografa -> encode -> $askdjsaldh23h3j
        // $ahsd340349asdaskdlas
        String senhaCriptografada = encoder.encode(dto.getSenha());
        if (encoder.matches(dto.getSenha(), usuario.getSenha())) {
            String token = jwtService.gerarToken(usuario.getEmail());
            return Optional.of(new TokenDTO(token));
        }
        return Optional.empty();
    }

    private UsuarioDTO toDto(Usuario entity) {
        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getEmail());
    }
}
