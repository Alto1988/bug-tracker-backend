package com.example.bugbackend.controllers;

import com.example.bugbackend.models.Team;
import com.example.bugbackend.models.User;
import com.example.bugbackend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class TeamController {


    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    //Post Mappings
    @PostMapping("/bug/{id}/team")
    public Team createTeam(@PathVariable(value= "id") Long id, @RequestBody Team teamMember) {
        return teamService.createTeam(id, teamMember);
    }

    //Get Mappings


    //Put Mappings


    //Delete Mappings

}
