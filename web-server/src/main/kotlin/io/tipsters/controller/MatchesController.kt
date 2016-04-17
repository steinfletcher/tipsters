package io.tipsters.controller

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.service.MatchService
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/matches")
class MatchesController @Autowired constructor(val matchService: MatchService) {

    @RequestMapping(method = arrayOf(POST))
    fun matchesByCompetitions(@Valid @RequestBody request: MatchesByCompetitions): List<MatchesByCompetition> {
        return matchService.matchesByCompetitions(request.competitionIDs)
    }
}

data class MatchesByCompetitions (
    @get:NotEmpty
    val competitionIDs: Set<UUID>
)