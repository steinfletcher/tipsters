package io.tipsters.uiapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonDeserialize(builder = MatchesRequest.Builder.class)
public class MatchesRequest {
  @NotEmpty
  private final Set<UUID> competitionIDs;
  private final LocalDateTime start;
  private final LocalDateTime end;

  public MatchesRequest(Set<UUID> competitionIDs, LocalDateTime start, LocalDateTime end) {
    this.competitionIDs = competitionIDs;
    this.start = start;
    this.end = end;
  }

  public Set<UUID> getCompetitionIDs() {
    return competitionIDs;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  @JsonPOJOBuilder
  public static class Builder {

    private Set<UUID> competitionsIDs;
    private LocalDateTime start;
    private LocalDateTime end;

    public Builder withCompetitionIDs(Set<UUID> competitionsIDs) {
      this.competitionsIDs = competitionsIDs;
      return this;
    }

    public Builder withStart(LocalDateTime start) {
      this.start = start;
      return this;
    }

    public Builder withEnd(LocalDateTime end) {
      this.end = end;
      return this;
    }

    public MatchesRequest build() {
      return new MatchesRequest(competitionsIDs, start, end);
    }
  }
}