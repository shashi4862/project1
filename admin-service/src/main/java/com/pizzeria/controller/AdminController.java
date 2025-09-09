package com.pizzeria.controller;

import com.pizzeria.entity.Admin;
import com.pizzeria.entity.MenuItem;
import com.pizzeria.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Register admin
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.register(admin));
    }

    // Login admin
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,
                                        @RequestParam String password){
        return ResponseEntity.ok(adminService.login(email, password));
    }

    // Add menu item with optional image
    @PostMapping("/menu")
    public ResponseEntity<MenuItem> addMenuItem(@RequestPart MenuItem menuItem,
                                                @RequestPart(required = false) MultipartFile image){
        return ResponseEntity.ok(adminService.addMenuItem(menuItem, image));
    }

    // Update menu item
    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id,
                                                   @RequestPart MenuItem menuItem,
                                                   @RequestPart(required = false) MultipartFile image){
        return ResponseEntity.ok(adminService.updateMenuItem(id, menuItem, image));
    }

    // Delete menu item
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id){
        adminService.deleteMenuItem(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    // Get all menu items
    @GetMapping("/menu")
    public ResponseEntity<List<MenuItem>> getAllMenuItems(){
        return ResponseEntity.ok(adminService.getAllMenuItems());
    }

    // Get menu item by ID
    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getMenuItemById(id));
    }
}