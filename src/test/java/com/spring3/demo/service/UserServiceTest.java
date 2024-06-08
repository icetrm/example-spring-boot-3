package com.spring3.demo.service;

import com.spring3.demo.domain.response.UserResponse;
import com.spring3.demo.entity.User;
import com.spring3.demo.exception.ResourceNotFoundException;
import com.spring3.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers_Success() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User("John"));
        userList.add(new User("Alice"));
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<UserResponse> userResponseList = userService.getAllUsers();

        // Assert
        assertNotNull(userResponseList);
        assertEquals(2, userResponseList.size());
        assertEquals("John", userResponseList.get(0).getName());
        assertEquals("Alice", userResponseList.get(1).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_Exception() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getAllUsers());
        verify(userRepository, times(1)).findAll();
    }
}
