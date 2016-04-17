package io.tipsters.common.oddsprovider

import io.tipsters.common.data.MatchesByCompetition

interface OddsProvider {
    fun odds(competitionNames: Set<String>): List<MatchesByCompetition>

    fun providesCompetitions(): Set<String>
}
