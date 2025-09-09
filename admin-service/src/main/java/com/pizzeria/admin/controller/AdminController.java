package com.pizzeria.admin.controller;

import com.pizzeria.admin.model.User;
import com.pizzeria.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Endpoint to get a list of all users.
     * In a production environment, this would be protected by Spring Security,
     * allowing access only to users with an "ADMIN" role.
     * @return A ResponseEntity containing a list of all User objects.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint to update the role of a specific user.
     * In a production environment, this would also be protected by a role-based
     * access control mechanism.
     * @param userId The ID of the user whose role is to be updated.
     * @param newRole The new role to assign to the user.
     * @return A ResponseEntity with the updated User object.
     */
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<User> updateUserRole(@PathVariable Long userId, @RequestParam String newRole) {
        User updatedUser = adminService.updateUserRole(userId, newRole);
        return ResponseEntity.ok(updatedUser);
    }
}
