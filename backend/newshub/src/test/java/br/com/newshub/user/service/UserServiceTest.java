package br.com.newshub.user.service;

import br.com.newshub.response.ResponseModel;
import br.com.newshub.user.dto.request.UserRequest;
import br.com.newshub.user.dto.response.UserResponse;
import br.com.newshub.user.mapper.UserMapper;
import br.com.newshub.user.model.User;
import br.com.newshub.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserRequest userRequest;
    private User userModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userRequest = new UserRequest("User", "teste@email.com", "1234");

        userModel = User.builder()
                .id(1L)
                .username(userRequest.name())
                .email(userRequest.email())
                .password("hashedPassword")
                .build();
    }

    // Teste
    @Test
    @DisplayName("Must create a user")
    void mustCreateUser() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(userModel);

        // Act
        ResponseModel<UserResponse> response = userService.createUser(userRequest);

        // Assert
        assertNotNull(response);
        assertNotNull(response.data());
        assertEquals("User", response.data().name());
        assertEquals("teste@email.com", response.data().email());

        verify(userRepository).save(any(User.class));
    }
}
