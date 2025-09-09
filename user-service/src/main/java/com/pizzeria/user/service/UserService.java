package com.pizzeria.user.service;

import com.pizzeria.user.exception.InvalidInputException;
import com.pizzeria.user.model.User;
import com.pizzeria.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user with a default role of "USER".
     * Validates that the username is not empty and is not already taken.
     * @param user The User object to register.
     * @return The registered User object.
     * @throws InvalidInputException if the username is empty or already exists.
     */
    public User registerUser(User user) {
        // Basic validation and business logic
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidInputException("Username cannot be empty");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidInputException("Username already exists");
        }
        // Set default role for new user
        user.setRole("USER");
        return userRepository.save(user);
    }

    /**
     * Authenticates a user based on their username and password.
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return The authenticated User object.
     * @throws InvalidInputException if the username or password is incorrect.
     */
    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new InvalidInputException("Invalid username or password");
    }
}
