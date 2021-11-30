package com.example.springboot_311.service;

import com.example.springboot_311.model.User;
import com.example.springboot_311.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void save(User newUser) {
        Long userId = newUser.getId();
        if (userId == null) {
            userRepository.save(newUser);
            return;
        } else {
            User user = userRepository.findById(userId).get();
            user.setUsername(newUser.getUsername());
            user.setDepartment(newUser.getDepartment());
            user.setSalary(newUser.getSalary());
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(name);
        return user;
    }
}
