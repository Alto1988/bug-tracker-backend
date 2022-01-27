package com.example.bugbackend.controllers;

import com.example.bugbackend.models.User;
import com.example.bugbackend.models.auth.request.LoginRequest;
import com.example.bugbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USERS

    /** Returns a list of users. Is mainly used for testing purposes.
     * @return List of existing uses registered in the application.
     */
    @GetMapping("/")
    public List<User> getAllUsers() {
        LOGGER.info("Calling getAllUsers() method from UserController!");
        return userService.getAllUsers();
    }

    // CREATE NEW USER

    /** Creates a new user.
     * @param userObject The user object containing the necessary user data.
     * @return The newly created user.
     */
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        LOGGER.info("Calling createUser() method from UserController!");
        return userService.createUser(userObject);
    }

    // LOGIN USER

    /** Logs a user in and enables them to perform various requests.
     * @param loginRequest The login request containing necessary user information to authorize access.
     * @return JSON Web Token to permit user to execute requests.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("Calling login() method from UserController!");
        return userService.loginUser(loginRequest);
    }
}
