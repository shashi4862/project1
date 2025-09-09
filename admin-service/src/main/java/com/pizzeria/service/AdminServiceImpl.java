package com.pizzeria.service;

import com.pizzeria.entity.Admin;
import com.pizzeria.entity.MenuItem;
import com.pizzeria.entity.Role;
import com.pizzeria.exception.CustomException;
import com.pizzeria.repository.AdminRepository;
import com.pizzeria.repository.MenuItemRepository;
import com.pizzeria.security.JwtUtil;
import com.pizzeria.util.ImageUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final MenuItemRepository menuItemRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Admin register(Admin admin) {
        if(adminRepository.findByEmail(admin.getEmail()).isPresent()){
            throw new CustomException("Email already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        return adminRepository.save(admin);
    }

    @Override
    public String login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Invalid email or password"));

        if(!passwordEncoder.matches(password, admin.getPassword())){
            throw new CustomException("Invalid email or password");
        }
        return jwtUtil.generateToken(admin.getEmail());
    }

    @Override
    public MenuItem addMenuItem(MenuItem menuItem, MultipartFile image) {
        if(image != null && !image.isEmpty()){
            String path = ImageUploadUtil.saveImage(image);
            menuItem.setImagePath(path);
        }
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem, MultipartFile image) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Menu item not found"));
        existing.setName(menuItem.getName());
        existing.setCategory(menuItem.getCategory());
        existing.setPrice(menuItem.getPrice());
        existing.setStockQuantity(menuItem.getStockQuantity());
        if(image != null && !image.isEmpty()){
            String path = ImageUploadUtil.saveImage(image);
            existing.setImagePath(path);
        }
        return menuItemRepository.save(existing);
    }

    @Override
    public void deleteMenuItem(Long id) {
        if(!menuItemRepository.existsById(id)){
            throw new CustomException("Menu item not found");
        }
        menuItemRepository.deleteById(id);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Menu item not found"));
    }
}

