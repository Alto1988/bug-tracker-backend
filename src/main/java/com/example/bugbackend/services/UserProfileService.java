package com.example.bugbackend.services;

import com.example.bugbackend.exceptions.InformationDoesNotExistException;
import com.example.bugbackend.exceptions.InformationExistsException;
import com.example.bugbackend.models.User;
import com.example.bugbackend.models.UserProfile;
import com.example.bugbackend.repository.UserProfileRepository;
import com.example.bugbackend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserProfileService {

    private static final Logger LOGGER = Logger.getLogger(UserProfileService.class.getName());
    private UserProfileRepository userProfileRepository;


    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserProfile() {
        LOGGER.info("Calling getUserProfile()");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserProfile user = userProfileRepository.findUserProfileByName(userDetails.getUser().getUsername());
        LOGGER.info("UserProfileService: getUserProfile: userProfile: " + user);
        if(user == null) {
            throw new InformationDoesNotExistException("user does not exist " + userDetails.getUsername());
        }else {
            return user;
        }
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Check if user already exists
        UserProfile user = userProfileRepository.findUserProfileByName(userDetails.getUser().getUsername());
        if(user != null) {
            //If user exists, throw exception
            throw new InformationExistsException("user already exists " + userDetails.getUsername());
        }else{
            //If user does not exist, create user
            userProfile.setName(userProfile.getName());
            userProfile.setDeveloperLevel(userProfile.getDeveloperLevel());
            userProfileRepository.save(userProfile);
            return userProfile;
        }
    }
}
