package io.tipsters.uiapi.client.williamhill;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import io.tipsters.uiapi.dto.CompetitionMatches;

public interface OddsProvider {
  List<CompetitionMatches> odds(Set<String> competitionNames, LocalDateTime matchStart, LocalDateTime matchEnd);

  Set<String> providesCompetitions();
}
