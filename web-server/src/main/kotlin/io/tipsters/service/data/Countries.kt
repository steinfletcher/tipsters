package io.tipsters.service.data

import io.tipsters.domain.Competition
import io.tipsters.domain.Country
import io.tipsters.domain.Region
import java.util.*

data class CompetitionData(val id: UUID, val name: String, val rank: Int) {
    companion object {
        fun of(competition: Competition): CompetitionData {
            return CompetitionData(
                    id = competition.id,
                    name = competition.name,
                    rank = competition.rank)
        }
    }
}

data class CountryData(val id: UUID, val name: String, val competitions: List<CompetitionData>, val rank: Int, val region: Region) {
    companion object {
        fun of(country: Country): CountryData {
            val competitions = country.competitions.map { c -> CompetitionData.of(c) }
            return CountryData(
                    id = country.id,
                    name = country.name,
                    competitions = competitions,
                    rank = country.rank,
                    region = country.region)
        }
    }
}
