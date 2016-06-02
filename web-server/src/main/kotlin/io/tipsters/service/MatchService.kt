package io.tipsters.service

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.controller.MatchesByCompetitions
import io.tipsters.error.CompetitionNotFoundError
import io.tipsters.repository.CompetitionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class MatchService @Autowired constructor(val oddsProviderFactory: OddsProviderFactory,
                                               val matchesRepository: CompetitionRepository) {

    open fun matchesByCompetitions(request: MatchesByCompetitions): List<MatchesByCompetition> {
        val competitionNames: Set<String> = matchesRepository.findCompetitionNamesByIdIn(
                request.competitionIDs.map { id -> id.toString() }
        )

        if (competitionNames.isEmpty()) {
            throw CompetitionNotFoundError("No competitions found")
        }

        val oddsProviders = oddsProviderFactory.providersForCompetitions(competitionNames)
        return oddsProviders.flatMap { provider -> provider.odds(competitionNames, request.start, request.end) }
    }
}
