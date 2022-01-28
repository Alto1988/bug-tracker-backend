package com.example.bugbackend.controllers;

import com.example.bugbackend.models.UserProfile;
import com.example.bugbackend.security.MyUserDetails;
import com.example.bugbackend.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserProfileController {

    private UserProfileService userProfileService;


    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    //START OF THE GET METHODS
    // http://localhost:9092/api/userprofile/getUserProfile
    @GetMapping("/userprofile")
    public UserProfile getUserProfile() {
     return userProfileService.getUserProfile();
    }

    //END OF THE GET METHODS

    //START OF THE POST METHODS

    // http://localhost:9092/api/userprofile/updateUserProfile
    @PostMapping("/userprofile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.createUserProfile(userProfile);
    }

    //END OF THE POST METHODS
}
