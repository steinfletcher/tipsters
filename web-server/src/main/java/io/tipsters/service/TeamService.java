package io.tipsters.service;

import io.tipsters.domain.Team;
import io.tipsters.error.TeamNotFoundError;
import io.tipsters.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByName(String teamName) {
        return teamRepository
                .findOneByName(teamName)
                .orElseThrow(() -> new TeamNotFoundError("No team with name " + teamName));
    }
}
