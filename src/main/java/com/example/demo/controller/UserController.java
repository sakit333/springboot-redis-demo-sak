package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String id, @RequestParam String name, Model model) {
        userService.saveUser(new User(id, name));
        model.addAttribute("message", "✅ User saved to Redis successfully!");
        return "index";
    }

    @GetMapping("/get")
    public String getUser(@RequestParam String id, Model model) {
        User user = userService.getUser(id);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("message", "❌ User not found for ID: " + id);
        }
        return "index";
    }
}
