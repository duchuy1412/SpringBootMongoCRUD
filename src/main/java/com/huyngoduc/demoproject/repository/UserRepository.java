package com.huyngoduc.demoproject.repository;

import com.huyngoduc.demoproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
//  Page<User> findAllByLastNameContainsOrFirstNameContains(String name);
  Page<User> findAllByLastNameContaining(Pageable pr, String name);
}
