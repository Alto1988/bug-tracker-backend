package com.example.bugbackend.controllers;

import com.example.bugbackend.models.Bug;
import com.example.bugbackend.repository.BugRepository;
import com.example.bugbackend.services.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BugController {

    private BugService bugService;

    @Autowired
    public void setBugService(BugService bugService) {
        this.bugService = bugService;
    }

    //Get Mapping
    @GetMapping("/bug/{id}")
    public Bug getBug(@PathVariable Long id) {
        return bugService.getBug(id);
    }

    @GetMapping("/bug")
    public List<Bug> getBugs() {
        return bugService.getBugs();
    }

    //POST Mappings
    @PostMapping("/bug")
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);
    }


    //PUT Mappings
    @PutMapping("/bug/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        return bugService.updateBug(id, bug);
    }


    //Delete Mappings
    @DeleteMapping("/bug/{id}")
    public void deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
    }
}
