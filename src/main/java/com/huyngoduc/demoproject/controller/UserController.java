package com.huyngoduc.demoproject.controller;

import com.huyngoduc.demoproject.model.User;
import com.huyngoduc.demoproject.service.UserService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1") int pageNo,
                        @RequestParam(defaultValue = "10") int pageSize, @RequestParam(value = "search", required = false) String q, Model model) {
        Page<User> users = userService.findAll(PageRequest.of(pageNo - 1, pageSize));
        model.addAttribute("users", users);

//        if (q != null) {
//            //search
//            Page<User> searchUsers = userService.findUserByName(PageRequest.of(pageNo - 1, 50), q);
//            model.addAttribute("users", searchUsers);
//        }

        return "index";
    }

    @RequestMapping("search")
    public String search(@RequestParam(defaultValue = "1") int pageNo,
                         @RequestParam(defaultValue = "10") int pageSize, @RequestParam(value = "q", required = false) String q, Model model) {

        Page<User> searchUsers = userService.findUserByName(PageRequest.of(pageNo - 1, pageSize), q);
        model.addAttribute("users", searchUsers);
        model.addAttribute("query", q);

        return "search";
    }


    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping(value = "/profile")
    public String profileUser(@RequestParam("id") String userId, Model model) {
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            if (optionalUser.get().getAvatar() != null) {
                model.addAttribute("image",
                        Base64.getEncoder().encodeToString(optionalUser.get().getAvatar().getData()));
            }
        }
        return "profileUser";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam("id") String userId, Model model) {
        Optional<User> userEdit = userService.findUserById(userId);
        if (userEdit.isPresent()) {
            model.addAttribute("user", userEdit.get());
            if (userEdit.get().getAvatar() != null) {
                model.addAttribute("image",
                        Base64.getEncoder().encodeToString(userEdit.get().getAvatar().getData()));
            }
        }
        return "editUser";
    }

    @PostMapping("/save")
    public String save(User user, @RequestParam("image") MultipartFile image) throws IOException {
        user.setAvatar(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") String userId, Model model) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

//    @GetMapping("/{pageNo}/{pageSize}")
//    public String getPaginatedUser(@PathVariable int pageNo,
//                                   @PathVariable int pageSize, Model model) {
//        Page<User> users = userService.findAll(PageRequest.of(pageNo - 1, pageSize));
//
//        model.addAttribute("users", users);
//
//        return "index";
//    }
}
