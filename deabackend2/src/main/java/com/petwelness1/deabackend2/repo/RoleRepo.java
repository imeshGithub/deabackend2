package com.petwelness1.deabackend2.repo;

import com.petwelness1.deabackend2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepo  extends JpaRepository<Role,String> {
    Role findByRoleName(String roleName);
}
