package com.example.bugbackend.security;

import com.example.bugbackend.models.User;
import com.example.bugbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(MyUserDetailsService.class.getName());


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername");
        User user = userService.findUserByEmailAddress(email);
        return new MyUserDetails(user);
    }
}
