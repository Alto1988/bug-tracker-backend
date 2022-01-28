package com.example.bugbackend.services;

import com.example.bugbackend.models.User;
import com.example.bugbackend.models.UserProfile;
import com.example.bugbackend.repository.UserProfileRepository;
import com.example.bugbackend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;


    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserProfile(String filter) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserProfile user = userProfileRepository.findUserProfileByName(userDetails.getUsername());
        return null;
    }
}
