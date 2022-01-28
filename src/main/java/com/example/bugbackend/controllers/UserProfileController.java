package com.example.bugbackend.controllers;

import com.example.bugbackend.models.UserProfile;
import com.example.bugbackend.security.MyUserDetails;
import com.example.bugbackend.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/userprofile")
public class UserProfileController {

    private UserProfileService userProfileService;


    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    //START OF THE GET METHODS
    // http://localhost:9092/api/userprofile/getUserProfile
    @GetMapping("/getUserProfile?filter={filter}")
    public UserProfile getUserProfile(@PathVariable String filter){
     return userProfileService.getUserProfile(filter);
    }

    //END OF THE GET METHODS

}
