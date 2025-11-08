package br.com.newshub.user.controller;

import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel<UserResponse>> createUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(userService.createUser(UserMapper.toUser(request)));
    }

}
