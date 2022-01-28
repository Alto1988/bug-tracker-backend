package com.example.bugbackend.repository;

import com.example.bugbackend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileByName(String username);
}
