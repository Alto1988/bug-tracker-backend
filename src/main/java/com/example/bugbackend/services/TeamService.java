package com.example.bugbackend.services;

import com.example.bugbackend.exceptions.InformationDoesNotExistException;
import com.example.bugbackend.models.Bug;
import com.example.bugbackend.models.Team;
import com.example.bugbackend.models.User;
import com.example.bugbackend.repository.BugRepository;
import com.example.bugbackend.repository.TeamRepository;
import com.example.bugbackend.repository.UserRepository;
import com.example.bugbackend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private UserRepository userRepository;
    private BugRepository bugRepository;


    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBugRepository(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public Team createTeam(Long id, Team team) {
        MyUserDetails userDetails = (MyUserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getUser().getId());
        if (user.isPresent()) {
            Optional<Bug> bug = bugRepository.findById(id);
            if (bug.isPresent()) {
                team.setTeamName(team.getTeamName());
                team.setTeamMembers(team.getTeamMembers());
                return teamRepository.save(team);
            }else {
                throw new InformationDoesNotExistException("Bug with id " + id + " does not exist");
            }
        }else {
            throw new InformationDoesNotExistException("User does not exist");
        }
    }
}
