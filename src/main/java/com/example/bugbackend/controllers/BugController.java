package com.example.bugbackend.controllers;

import com.example.bugbackend.models.Bug;
import com.example.bugbackend.repository.BugRepository;
import com.example.bugbackend.services.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BugController {

    private BugService bugService;

    @Autowired
    public void setBugService(BugService bugService) {
        this.bugService = bugService;
    }


    //POST Mappings
    @PostMapping("/bug")
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);

    }
}
