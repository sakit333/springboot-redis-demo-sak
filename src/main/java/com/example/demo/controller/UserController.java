package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Add user → clears any previously fetched user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("successMessage", "✅ User added successfully!");
        // Ensure no user info is shown after adding
        model.addAttribute("user", null);
        return "index";
    }

    // Fetch user by ID → only shows when fetched
    @PostMapping("/fetch")
    public String fetchUser(@RequestParam String id, Model model) {
        User user = userService.getUser(id);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("errorMessage", "❌ No user found with ID: " + id);
        }
        return "index";
    }

    // Delete user → only deletes currently fetched user
    @PostMapping("/delete")
    public String deleteUser(@RequestParam String id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("successMessage", "🗑️ User deleted successfully!");
        // Clear displayed user after deletion
        model.addAttribute("user", null);
        return "index";
    }
}
