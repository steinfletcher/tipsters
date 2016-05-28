package io.tipsters.service

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.error.CompetitionNotFoundError
import io.tipsters.repository.CompetitionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.util.*

@Component
open class MatchService @Autowired constructor(val oddsProviderFactory: OddsProviderFactory,
                                               val matchesRepository: CompetitionRepository) {

    @Cacheable("matchesByCompetitions")
    open fun matchesByCompetitions(competitionIDs: Set<UUID>): List<MatchesByCompetition> {
        val competitionNames: Set<String> = matchesRepository.findCompetitionNamesByIdIn(
                competitionIDs.map { id -> id.toString() }
        )

        if (competitionNames.isEmpty()) {
            throw CompetitionNotFoundError("No competitions found")
        }

        val oddsProviders = oddsProviderFactory.providersForCompetitions(competitionNames)
        return oddsProviders.flatMap { provider -> provider.odds(competitionNames) }
    }
}
