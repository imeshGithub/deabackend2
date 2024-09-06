package com.petwelness1.deabackend2.service;

import com.petwelness1.deabackend2.entity.Role;
import com.petwelness1.deabackend2.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;




    public Role createNewRole(Role role){
        return roleRepo.save(role);
    }
}
