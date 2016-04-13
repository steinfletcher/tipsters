package io.tipsters.service

import io.tipsters.error.OddsApiError
import io.tipsters.oddsfeedclient.WilliamHillClient
import io.tipsters.oddsfeedclient.domain.Competition
import io.tipsters.oddsfeedclient.parser.WillHillOddsFeedParser
import io.tipsters.repository.CompetitionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MatchService @Autowired constructor(val oddsClient: WilliamHillClient, val matchesRepository: CompetitionRepository) {
    fun matchesByCompetitions(competitionIDs: List<UUID>): List<Competition> {
        val request = oddsClient.matches()
        val response = request.execute()
        if (response.isSuccessful) {
            val odds = WillHillOddsFeedParser(response.body().byteStream()).parse()
            val competitionNames: List<String> = matchesRepository.findByIdIn(competitionIDs).map { c -> c.name }
            return odds.competitions.filter { competition -> competitionNames.contains(competition.name) }
        } else {
            throw OddsApiError("Failed to retrieve matches from upstream API")
        }
    }
}
