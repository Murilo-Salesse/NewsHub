package br.com.newshub.user.service;

import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.model.User;
import br.com.newshub.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseModel<UserResponse> createUser(User user) {

        return ResponseModel.of(UserMapper.toUserResponse(userRepository.save(user)), "Usuário criado com sucesso");
    }
}
