package edu.amanuelhaile.Products.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.amanuelhaile.Products.data.UsersRepository;
import edu.amanuelhaile.Products.models.UserEntity;

@Controller
public class AuthController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()
                || confirmPassword == null || confirmPassword.isBlank()) {

            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        if (usersRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setEnabled(true);

        usersRepository.save(user);

        return "redirect:/login?registered";
    }
}