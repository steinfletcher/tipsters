package io.tipsters.uiapi.service;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.dto.MatchesRequest;
import io.tipsters.uiapi.repository.CompetitionRepository;

import static java.util.stream.Collectors.toList;

@Service
public class MatchesService {

  private final OddsProviderFactory oddsProviderFactory;
  private final CompetitionRepository competitionRepository;

  public MatchesService(OddsProviderFactory oddsProviderFactory, CompetitionRepository competitionRepository) {
    this.oddsProviderFactory = oddsProviderFactory;
    this.competitionRepository = competitionRepository;
  }

  public List<CompetitionMatches> matchesByCompetitions(MatchesRequest matchesRequest) {
    Set<String> competitionNames = competitionRepository.findCompetitionNamesByIdIn(matchesRequest.getCompetitionIDs()
        .stream()
        .map(UUID::toString)
        .collect(toList()));

    LocalDateTime start = matchesRequest.getStart() != null ? matchesRequest.getStart() : LocalDateTime.MIN;
    LocalDateTime end = matchesRequest.getEnd() != null ? matchesRequest.getEnd() : LocalDateTime.MAX;

    return oddsProviderFactory.providersForCompetitions(competitionNames)
        .stream()
        .map(oddsProvider -> oddsProvider.odds(competitionNames, start, end))
        .flatMap(List::stream)
        .collect(toList());
  }
}
