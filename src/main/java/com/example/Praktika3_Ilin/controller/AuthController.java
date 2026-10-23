package com.example.Praktika3_Ilin.controller;

import com.example.Praktika3_Ilin.model.User;
import com.example.Praktika3_Ilin.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-form";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (userService.authenticate(username, password)) {
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("currentUser", username);
            return "redirect:/students";
        }

        model.addAttribute("error", "Неверный логин или пароль");
        return "login-form";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register-form";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
