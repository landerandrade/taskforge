package br.com.docodigoaocontrato.taskforge.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 10, message = "O nome deve ter no máximo 10 caracteres")
    private String nome;

    @NotNull(message = "A prioridade é obrigatória")
    @Min(value = 1, message = "A prioridade deve estar entre 1 e 5")
    @Max(value = 5, message = "A prioridade deve estar entre 1 e 5")
    private Integer prioridade;

    @NotNull(message = "Status de conclusão é obrigatório")
    private Boolean concluida;
}
