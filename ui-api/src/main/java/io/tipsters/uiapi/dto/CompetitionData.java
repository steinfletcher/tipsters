package io.tipsters.uiapi.dto;

import java.util.UUID;

import io.tipsters.uiapi.domain.Competition;

public class CompetitionData {
  private final UUID id;
  private final String name;
  private final int rank;
  private final boolean active;

  public CompetitionData(UUID id, String name, int rank, boolean active) {
    this.id = id;
    this.name = name;
    this.rank = rank;
    this.active = active;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getRank() {
    return rank;
  }

  public boolean isActive() {
    return active;
  }

  public static CompetitionData of(Competition competition) {
    return new CompetitionData.Builder()
        .withId(competition.getId())
        .withName(competition.getName())
        .withRank(competition.getRank())
        .withActive(competition.isActive())
        .build();
  }

  public static class Builder {

    private UUID id;
    private String name;
    private int rank;
    private boolean active;

    public Builder withId(UUID id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withRank(int rank) {
      this.rank = rank;
      return this;
    }

    public Builder withActive(boolean active) {
      this.active = active;
      return this;
    }

    public CompetitionData build() {
      return new CompetitionData(id, name, rank, active);
    }
  }
}
