package com.pizzeria.user.controller;

import com.pizzeria.user.model.User;
import com.pizzeria.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pizzeria.user.exception.InvalidInputException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to register a new user.
     * @param user The User object containing registration details.
     * @return A ResponseEntity with the registered User and a 201 Created status.
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    /**
     * Endpoint to authenticate a user.
     * @param user The User object containing login credentials (username and password).
     * @return A ResponseEntity with the authenticated User and a 200 OK status.
     */
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.loginUser(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }
}
