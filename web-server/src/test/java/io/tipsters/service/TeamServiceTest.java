package io.tipsters.service;

import io.tipsters.domain.Team;
import io.tipsters.error.TeamNotFoundError;
import io.tipsters.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static io.tipsters.testsupport.Fixtures.team;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TeamServiceTest {
    private TeamRepository repository;
    private TeamService underTest;

    @Before
    public void setUp() throws Exception {
        repository = mock(TeamRepository.class);
        underTest = new TeamService(repository);
    }

    @Test
    public void returnsTheTeamByName() throws Exception {
        String teamName = "Arsenal";
        given(repository.findOneByName(teamName)).willReturn(Optional.of(team(teamName)));

        Team team = underTest.findByName(teamName);

        assertThat(team.getName(), is(teamName));
    }

    @Test(expected = TeamNotFoundError.class)
    public void throwsNotFoundIfTeamNotFound() throws Exception {
        String teamName = "idontexist";
        given(repository.findOneByName(teamName)).willReturn(Optional.empty());

        underTest.findByName(teamName);
    }
}