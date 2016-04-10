package io.tipsters.testsupport;

import io.tipsters.domain.Team;

public class Fixtures {
    public static Team team(String name) {
        Team team = new Team();
        team.setName(name);
        return team;
    }
}
