package br.com.newshub.user.mapper;

import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    // DTO (Request) → Model (Entidade)
    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    // Model (Entidade) → DTO (Response)
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}

