package br.com.cep.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCdadstroUsuario(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        @Valid
        String login,
        @NotBlank
        String senha
) {
}
