package com.jbond.shaurmito.repo;

import com.jbond.shaurmito.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}