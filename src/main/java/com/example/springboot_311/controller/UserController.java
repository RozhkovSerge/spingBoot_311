package com.example.springboot_311.controller;

import com.example.springboot_311.model.User;
import com.example.springboot_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showUsers(Model model) {
        User user = new User();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("title", "Add user");
        model.addAttribute("user", user);
        return "show-users";
    }

    @PostMapping("/admin/user")
    public String addUser(@ModelAttribute(value = "user") User user) {
        user.setPassword("pass");
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("title", "Edit user");
        return "/show-users";
    }

    @GetMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        userService.remove(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String showUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user_page";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
}

