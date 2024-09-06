package com.petwelness1.deabackend2.service;

import com.petwelness1.deabackend2.entity.Role;
import com.petwelness1.deabackend2.entity.User;
import com.petwelness1.deabackend2.repo.RoleRepo;
import com.petwelness1.deabackend2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);

    }

    public void initRoleAndUser(){
        Role adminRole = new Role();
        Role userRole = new Role();
        if(!roleRepo.existsById("Admin")) {
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleRepo.save(adminRole);
        }

        if(!roleRepo.existsById("User")) {
            userRole.setRoleName("User");
            userRole.setRoleDescription("User role");
            roleRepo.save(userRole);
        }
        if(!userRepo.existsById("admin123")) {
            User user = new User();
            user.setUsername("admin123");
            user.setEmail("admin@gmail.com");
            user.setPassword(getEncodedPassword("admin@123"));
            user.setPetType("dog");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userRepo.save(user);
        }

        if(!userRepo.existsById("user123")) {
            User user = new User();
            user.setUsername("user123");
            user.setEmail("user@gmail.com");
            user.setPassword(getEncodedPassword("user@123"));
            user.setPetType("cat");

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userRepo.save(user);
        }

        }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);





    }
}
