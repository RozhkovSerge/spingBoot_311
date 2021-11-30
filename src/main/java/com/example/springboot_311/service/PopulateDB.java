package com.example.springboot_311.service;

import com.example.springboot_311.model.Role;
import com.example.springboot_311.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
public class PopulateDB {
    private UserService userService;

    @Autowired
    public PopulateDB(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void populateDB() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User user = new User();
        user.setUsername("user");
        user.setDepartment("Sales");
        user.setPassword("$2a$12$q98.zeMhcdtMh6.EIN/1eO9eJ5RbeU8G1Zl2Bo5zDMUulqYGjH3Pa"); // password is: 100
        user.getRoles().add(roleUser);
        userService.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setDepartment("IT");
        admin.setPassword("$2a$12$q98.zeMhcdtMh6.EIN/1eO9eJ5RbeU8G1Zl2Bo5zDMUulqYGjH3Pa"); // password is: 100
        admin.addRole(roleAdmin);
        userService.save(admin);

    }
}
