package edu.amanuelhaile.Products.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.amanuelhaile.Products.data.UsersRepository;
import edu.amanuelhaile.Products.models.UserEntity;

@Controller
public class UserAdminController {

    private final UsersRepository usersRepository;

    public UserAdminController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/admin/users")
    public String showUsers(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "userAdmin";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {

        UserEntity user = usersRepository.findById(id).orElse(null);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/admin/users/edit")
    public String processEditUser(
            @RequestParam int id,
            @RequestParam String role,
            @RequestParam(required = false) Boolean enabled) {

        UserEntity user = usersRepository.findById(id).orElse(null);

        if (user != null) {
            user.setRole(role);
            user.setEnabled(enabled != null && enabled);

            usersRepository.save(user);
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String confirmDeleteUser(@PathVariable int id, Model model) {

        UserEntity user = usersRepository.findById(id).orElse(null);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);
        return "confirmDeleteUser";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam int id) {

        usersRepository.deleteById(id);

        return "redirect:/admin/users";
    }
}