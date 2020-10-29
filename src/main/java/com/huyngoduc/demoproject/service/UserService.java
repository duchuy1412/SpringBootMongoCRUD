package com.huyngoduc.demoproject.service;

import com.huyngoduc.demoproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> findAll(PageRequest pr);

    Optional<User> findUserById(String id);

    void saveUser(User user);

    void deleteUser(String id);

    Page<User> findUserByName(Pageable pr, String name);

}


