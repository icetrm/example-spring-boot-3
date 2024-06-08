package com.spring3.demo.service;

import com.spring3.demo.domain.response.UserResponse;
import com.spring3.demo.exception.ResourceNotFoundException;
import com.spring3.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        logger.info("getAllUsers");
        List<UserResponse> userResponseList;
        try {
            userResponseList = userRepository.findAll().stream().map(x -> new UserResponse(x.getFirstName())).toList();
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return userResponseList;
    }

}
