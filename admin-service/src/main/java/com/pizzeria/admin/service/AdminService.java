package com.pizzeria.admin.service;

import com.pizzeria.admin.exception.InvalidInputException;
import com.pizzeria.admin.model.User;
import com.pizzeria.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users from the database.
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates the role of a user.
     * @param userId The ID of the user.
     * @param newRole The new role to set.
     * @return The updated User object.
     * @throws InvalidInputException if the user is not found.
     */
    public User updateUserRole(Long userId, String newRole) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(newRole);
            return userRepository.save(user);
        } else {
            throw new InvalidInputException("User not found with ID: " + userId);
        }
    }
}
