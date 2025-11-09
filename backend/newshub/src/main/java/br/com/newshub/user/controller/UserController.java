package br.com.newshub.user.controller;

import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<ResponseModel<List<UserResponse>>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }
}
