package com.huyngoduc.demoproject.service;

import com.huyngoduc.demoproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> findAll(PageRequest pr);

    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(String id);

    Optional<User> findUserById(String id);

    List<User> search(String username);

    List<User> findPaginated(int pageNo, int pageSize);
}


