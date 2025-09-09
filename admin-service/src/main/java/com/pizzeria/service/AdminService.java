package com.pizzeria.service;

import com.pizzeria.entity.Admin;
import com.pizzeria.entity.MenuItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {

    Admin register(Admin admin);
    String login(String email, String password);

    MenuItem addMenuItem(MenuItem menuItem, MultipartFile image);
    MenuItem updateMenuItem(Long id, MenuItem menuItem, MultipartFile image);
    void deleteMenuItem(Long id);
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(Long id);
}
