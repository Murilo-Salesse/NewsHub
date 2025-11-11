package br.com.newshub.user.service;

import br.com.newshub.configuration.JwtTokenUtil;
import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.request.UserRequestLogin;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.dto.response.UserResponseLogin;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.model.User;
import br.com.newshub.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseModel<UserResponse> createUser(UserRequest userRequest) {
        User user = UserMapper.toUser(userRequest, passwordEncoder);
        User savedUser = userRepository.save(user);

        return ResponseModel.of(
                UserMapper.toUserResponse(savedUser),
                "Usuário criado com sucesso"
        );
    }

    public ResponseModel<UserResponseLogin> verifyIfUserExists(UserRequestLogin requestLogin) {
        User user = userRepository.findByEmail(requestLogin.email())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        boolean passwordMatches = passwordEncoder.matches(requestLogin.password(), user.getPassword());

        if (!passwordMatches) {
            throw new EntityNotFoundException("Email ou Senha incorretos");
        }

        return ResponseModel.of(UserMapper.toUserResponseWithToken(user,
                        jwtTokenUtil.generateToken("", requestLogin.email())), "Login realizado com sucesso");
    }

    public ResponseModel<List<UserResponse>> listAllUsers() {

        List<User> users = userRepository.findAll();
        return ResponseModel.ok(UserMapper.toUserResponse(users));
    }
}
