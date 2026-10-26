package br.com.docodigoaocontrato.taskforge.exception;

import br.com.docodigoaocontrato.taskforge.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        for (FieldError erro : ex.getFieldErrors()) {
            campos.put(erro.getField(), erro.getDefaultMessage());
        }
        ErroResposta resposta = new ErroResposta(400, "Dados Inválidos", campos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarJsonInvalido(HttpMessageNotReadableException ex) {
        ErroResposta resposta = new ErroResposta(400, "JSON inválido: confira a estrutura, vírgulas, " +
                "chaves, colchetes, aspas", Map.of());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
