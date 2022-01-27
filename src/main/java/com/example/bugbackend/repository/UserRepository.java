package com.example.bugbackend.repository;

import com.example.bugbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
