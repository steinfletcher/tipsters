package io.tipsters.uiapi.dto;

import java.util.List;
import java.util.UUID;

import io.tipsters.uiapi.domain.Country;

import static java.util.stream.Collectors.toList;

public class CountryData {
  private final UUID id;
  private final String name;
  private final int rank;
  private final List<CompetitionData> competitions;

  public CountryData(UUID id, String name, int rank, List<CompetitionData> competitions) {
    this.id = id;
    this.name = name;
    this.rank = rank;
    this.competitions = competitions;
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

  public List<CompetitionData> getCompetitions() {
    return competitions;
  }

  public static CountryData of(Country country) {
    List<CompetitionData> competitions = country.getCompetitions().stream().map(CompetitionData::of).collect(toList());
    return new CountryData.Builder()
        .withCompetitions(competitions)
        .withRank(country.getRank())
        .withName(country.getName())
        .withId(country.getId())
        .build();
  }

  public static class Builder {

    private UUID id;
    private String name;
    private int rank;
    private List<CompetitionData> competitions;

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

    public Builder withCompetitions(List<CompetitionData> competitions) {
      this.competitions = competitions;
      return this;
    }

    public CountryData build() {
      return new CountryData(id, name, rank, competitions);
    }
  }
}
