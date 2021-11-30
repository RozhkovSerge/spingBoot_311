package com.example.springboot_311.repository;

import com.example.springboot_311.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username=:name")
    User findUserByUsername(String name);
}
