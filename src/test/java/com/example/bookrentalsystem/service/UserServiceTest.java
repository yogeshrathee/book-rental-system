package com.example.bookrentalsystem.service;

import com.example.bookrentalsystem.entity.User;
import com.example.bookrentalsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Mocking behavior for password encoder
        given(passwordEncoder.encode(any(CharSequence.class))).willReturn("hashed_password");
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        given(userRepository.save(any(User.class))).willReturn(user);

        User registeredUser = userService.register(user);

        assertEquals("test@example.com", registeredUser.getEmail());
        assertEquals("hashed_password", registeredUser.getPassword());
    }

}

