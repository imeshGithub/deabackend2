package com.petwelness1.deabackend2.repo;

import com.petwelness1.deabackend2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,String> {
    User findByUsername(String username);
}
