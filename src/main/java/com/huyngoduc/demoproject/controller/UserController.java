package com.huyngoduc.demoproject.controller;

import com.huyngoduc.demoproject.model.User;
import com.huyngoduc.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUser();

        model.addAttribute("users", users);

        return "index";
    }

    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam("id") String userId, Model model) {
        Optional<User> userEdit = userService.findUserById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }

    @PostMapping("/save")
    public String save(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") String userId, Model model) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchUsers(@RequestParam("username") String username, Model model) {
        List<User> users = userService.search(username);
        model.addAttribute("users", users);
        return "index";
    }
}
