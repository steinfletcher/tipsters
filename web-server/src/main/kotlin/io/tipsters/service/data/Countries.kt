package io.tipsters.service.data

import io.tipsters.domain.Competition
import io.tipsters.domain.Country
import java.util.*

data class CompetitionData(val id: UUID, val name: String, val rank: Int) {
    companion object {
        fun of(competition: Competition):CompetitionData {
            return CompetitionData(competition.id, competition.name, competition.rank)
        }
    }
}

data class CountryData(val id: UUID, val name: String, val competitions: List<CompetitionData>, val rank: Int) {
    companion object {
        fun of(country: Country): CountryData {
            return CountryData(country.id, country.name, country.competitions.map { c -> CompetitionData.of(c) }, country.rank)
        }
    }
}
