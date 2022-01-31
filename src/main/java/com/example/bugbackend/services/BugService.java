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

import java.time.LocalDate;
import java.time.LocalDateTime;
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

//        Bug tempBug = bugRepository.findBugByUserId(user.getUser().getId());
        //This checks if the user already has a bug with that title
        Bug tempBug = bugRepository.findBugByUserIdAndTitle(user.getUser().getId(), bug.getTitle());
        if (tempBug != null) {
            throw new InformationDoesNotExistException("User already has a bug");
        }else {
            bug.setCreatedAt(LocalDate.from(LocalDateTime.now()));
            bug.setUser(user.getUser());
            return bugRepository.save(bug);
        }
    }

    public Bug updateBug(Long id, Bug bug) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bug tempBug = bugRepository.findBugByUserId(user.getUser().getId());
        if (tempBug == null) {
            throw new InformationDoesNotExistException("User does not have a bug");
        }else{
            tempBug.setTitle(bug.getTitle());
            tempBug.setDescription(bug.getDescription());
            tempBug.setStatus(bug.getStatus());
            tempBug.setPriority(bug.getPriority());
            tempBug.setUpdatedAt(LocalDate.from(LocalDateTime.now()));
            tempBug.setResolutionSummary(bug.getResolutionSummary());
            tempBug.setUser(user.getUser());
            return bugRepository.save(tempBug);
        }
    }

    public void deleteBug(Long id) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Bug> bug = bugRepository.findById(id);
        if (bug.isPresent()) {
            Bug tempBug = bug.get();
            if (tempBug.getUser().getId() == user.getUser().getId()) {
                bugRepository.deleteById(id);
            }else {
                throw new InformationDoesNotExistException("User does not have a bug");
            }
        }else {
            throw new InformationDoesNotExistException("User does not have a bug");
        }

    }

    public Bug getBug(Long id) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Bug> bug = bugRepository.findById(id);
        if (bug.isPresent()) {
            Bug tempBug = bug.get();
            if (tempBug.getUser().getId() == user.getUser().getId()) {
                return tempBug;
            }else {
                throw new InformationDoesNotExistException("User does not have a bug");
            }
        }else {
            throw new InformationDoesNotExistException("User does not have a bug");
        }
    }
}
