package com.huyngoduc.demoproject.service;

import com.huyngoduc.demoproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(String id);

    Optional<User> findUserById(String id);

    List<User> search(String username);
}


