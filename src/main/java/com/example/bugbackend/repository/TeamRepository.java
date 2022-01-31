package com.example.bugbackend.repository;

import com.example.bugbackend.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByIdAndTeamName(Long id, String teamName);
}
