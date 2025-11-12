package br.com.newshub.user.dto.response;

import lombok.Builder;

@Builder
public record UserResponseLogin(
    Long id,
    String name,
    String email,
    String token
) {
}
