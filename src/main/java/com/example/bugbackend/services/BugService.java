package com.example.bugbackend.services;

import com.example.bugbackend.exceptions.InformationDoesNotExistException;
import com.example.bugbackend.models.Bug;
import com.example.bugbackend.models.User;
import com.example.bugbackend.repository.BugRepository;
import com.example.bugbackend.repository.UserRepository;
import com.example.bugbackend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BugService {

    private static final Logger LOGGER = Logger.getLogger(BugService.class.getName());

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private UserRepository userRepository;

    public Bug createBug(Bug bug) {
        LOGGER.info("Creating bug from createBug method");
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userOptional = userRepository.findUserByEmail(user.getUsername());
//        System.out.println(userOptional.get().getId());
        if(userOptional != null) {
            Bug returnBug = new Bug();
            returnBug.setTitle(bug.getTitle());
            returnBug.setDescription(bug.getDescription());
            returnBug.setCreatedAt(new Date());
            returnBug.setUpdatedAt(new Date());
            returnBug.setPriority(bug.getPriority());
            returnBug.setStatus(bug.getStatus());
            returnBug.setUser(userOptional);
            returnBug.setResolutionSummary(bug.getResolutionSummary());
            LOGGER.info("Bug created at " + new Date().toString());
            return bugRepository.save(returnBug);
        }else {
            throw new InformationDoesNotExistException("User does not exist");
        }
    }
}
