package io.tipsters.uiapi.dto;

import java.util.List;

public class CompetitionMatches {
  private final String competition;
  private final List<Match> matches;

  public CompetitionMatches(String competition, List<Match> matches) {
    this.competition = competition;
    this.matches = matches;
  }

  public String getCompetition() {
    return competition;
  }

  public List<Match> getMatches() {
    return matches;
  }

  public static class Builder {

    private String competition;
    private List<Match> matches;

    public Builder withCompetition(String competition) {
      this.competition = competition;
      return this;
    }

    public Builder withMatches(List<Match> matches) {
      this.matches = matches;
      return this;
    }

    public CompetitionMatches build() {
      return new CompetitionMatches(competition, matches);
    }
  }
}
