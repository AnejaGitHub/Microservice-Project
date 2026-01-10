package com.singh.fitness.userservice.service;

import com.singh.fitness.userservice.dto.RegisterRequest;
import com.singh.fitness.userservice.dto.UserResponse;
import com.singh.fitness.userservice.exception.UserAlreadyExistsException;
import com.singh.fitness.userservice.model.User;
import com.singh.fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();

        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        return userResponse;
    }

    public Object getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found!"));

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return userResponse;

    }
}
