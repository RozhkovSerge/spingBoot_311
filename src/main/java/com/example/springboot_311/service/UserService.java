package com.example.springboot_311.service;



import com.example.springboot_311.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String name);
    void save(User user);
    void remove(User user);

}
