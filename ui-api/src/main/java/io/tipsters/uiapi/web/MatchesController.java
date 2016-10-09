package io.tipsters.uiapi.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.dto.MatchesRequest;
import io.tipsters.uiapi.service.MatchesService;

@RestController
@RequestMapping("/matches")
public class MatchesController {

  private final MatchesService matchesService;

  public MatchesController(MatchesService matchesService) {
    this.matchesService = matchesService;
  }

  @PostMapping
  public List<CompetitionMatches> matchesByCompetition(@Valid @RequestBody MatchesRequest matchesRequest) {
    return matchesService.matchesByCompetitions(matchesRequest);
  }
}
