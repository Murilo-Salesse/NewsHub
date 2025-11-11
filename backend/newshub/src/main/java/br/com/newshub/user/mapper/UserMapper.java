package br.com.newshub.user.mapper;

import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.dto.response.UserResponseLogin;
import br.com.newshub.user.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    // DTO (Request) → Model (Entidade)
    public static User toUser(UserRequest userRequest, PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(userRequest.name())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
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

    public static UserResponseLogin toUserResponseWithToken(User user, String token) {
        return UserResponseLogin.builder()
                .id(user.getId())
                .name(user.getUsername())
                .email(user.getEmail())
                .token(token)
                .build();
    }


    // Model (Entidade) → DTO (Response) para listas
    public static List<UserResponse> toUserResponse(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }
}

