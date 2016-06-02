package io.tipsters.common.oddsprovider

import io.tipsters.common.data.MatchesByCompetition
import java.time.LocalDateTime

interface OddsProvider {
    fun odds(competitionNames: Set<String>, matchStart: LocalDateTime, matchEnd: LocalDateTime): List<MatchesByCompetition>

    fun providesCompetitions(): Set<String>
}
