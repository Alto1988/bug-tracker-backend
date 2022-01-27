package com.example.bugbackend.services;

import com.example.bugbackend.exceptions.InformationExistsException;
import com.example.bugbackend.models.User;
import com.example.bugbackend.models.auth.request.LoginRequest;
import com.example.bugbackend.models.auth.response.LoginResponse;
import com.example.bugbackend.repository.UserRepository;
import com.example.bugbackend.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /** Called from UserController to create a new user.
     * @param userObject The user object containing the information about the new user.
     * @return The newly created user.
     */
    public User createUser(User userObject) {
        LOGGER.info("Called createUser() from UserService");
        if(!userRepository.existsByEmail(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }else {
            throw new InformationExistsException("User with email: " + userObject.getEmail() + " already exists");
        }
    }

    /** Called from the UserController to log a user in.
     * @param loginRequest The login request containing the user authentication information.
     * @return Authorization Token
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        LOGGER.info("Called loginUser() from UserService");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    /** Finds a user by their email address.
     * @param emailAddress The email address of the user to search for.
     * @return The user with the matching email.
     */
    public User findUserByEmailAddress(String emailAddress){
        LOGGER.info("Called findUserByEmailAddress() from UserService");
        return userRepository.findUserByEmail(emailAddress);

    }

    /** Returns a list of all users registered. Used primarily for testing / admin purposes.
     * @return A list of all registered users.
     */
    public List<User> getAllUsers() {
        LOGGER.info("Called getAllUsers() from UserService");
        return userRepository.findAll();
    }
}
