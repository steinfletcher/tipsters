package io.tipsters.controller

import io.tipsters.oddsfeedclient.domain.Competition
import io.tipsters.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/matches")
class MatchesController @Autowired constructor(val matchService: MatchService) {

    @RequestMapping(method = arrayOf(POST))
    fun matchesByCompetitions(@RequestBody request: MatchesByCompetitions): List<Competition> {
        return matchService.matchesByCompetitions(request.competitionIDs)
    }
}

data class MatchesByCompetitions(val competitionIDs: List<UUID>)