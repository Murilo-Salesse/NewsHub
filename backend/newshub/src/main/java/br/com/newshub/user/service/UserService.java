package br.com.newshub.user.service;

import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.model.User;
import br.com.newshub.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseModel<UserResponse> createUser(UserRequest userRequest) {
        // Convert to DTO
        User user = UserMapper.toUser(userRequest);

        // Save
        User savedUser = userRepository.save(user);

        // Convert to user response
        return ResponseModel.of(
                UserMapper.toUserResponse(savedUser),
                "Usuário criado com sucesso"
        );
    }

    public ResponseModel<List<UserResponse>> listAllUsers() {

        List<User> users = userRepository.findAll();
        return ResponseModel.ok(UserMapper.toUserResponse(users));
    }
}
