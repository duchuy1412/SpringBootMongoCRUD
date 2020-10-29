package com.huyngoduc.demoproject.service.impl;

import com.huyngoduc.demoproject.model.User;
import com.huyngoduc.demoproject.repository.UserRepository;
import com.huyngoduc.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findUserByName(Pageable pr, String name) {
        return userRepository.findAllByLastNameContaining(pr, name);
    }

    public Page<User> findAll(PageRequest pr) {
        return userRepository.findAll(pr);
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

}
