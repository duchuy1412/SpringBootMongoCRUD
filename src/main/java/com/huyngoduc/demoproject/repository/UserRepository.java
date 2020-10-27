package com.huyngoduc.demoproject.repository;

import com.huyngoduc.demoproject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  List<User> findAllByUsername(String username);
}
