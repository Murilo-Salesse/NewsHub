package br.com.newshub.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequest(
   @NotBlank(message = "nome é obrigatório") String name,
   @NotBlank(message = "email é obrigatório")  String email,
   @NotBlank(message = "senha é obrigatória") String password
) {
}
