package br.com.cep.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadadstroUsuario(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String login,
        @NotBlank
        String senha
) {
}
