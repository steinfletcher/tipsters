package io.tipsters.service

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.config.OddsProviders
import org.junit.Assert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as eq

class OddsProviderFactoryTest {

    @Test
    fun returnsEmptyListIfProvidersAreEmpty() {
        val oddsProviders = OddsProviders(emptyList())
        val underTest = OddsProviderFactory(oddsProviders)

        val providers = underTest.providersForCompetitions(setOf("someCompetitionId"))

        assertThat(providers, eq(emptyList()))
    }

    @Test
    fun returnsOnlyTheProvidersRequested() {
        val underTest = OddsProviderFactory(someOddsProviders())

        val providersOfCompetition3 = underTest.providersForCompetitions(setOf("competition3"))

        assertThat(providersOfCompetition3.size, eq(1))
    }

    private fun someOddsProviders(): OddsProviders {
        val p1 = object : OddsProvider {
            override fun providesCompetitions(): Set<String> {
                return setOf("competition1", "competition2", "competition3")
            }
            override fun odds(competitionNames: Set<String>): List<MatchesByCompetition> {
                return listOf(
                        MatchesByCompetition("competition1", emptyList()),
                        MatchesByCompetition("competition2", emptyList()),
                        MatchesByCompetition("competition2", emptyList()),
                        MatchesByCompetition("competition3", emptyList()),
                        MatchesByCompetition("competition3", emptyList())
                )
            }
        }

        val p2 = object : OddsProvider {
            override fun providesCompetitions(): Set<String> {
                return setOf("competition1", "competition2")
            }
            override fun odds(competitionNames: Set<String>): List<MatchesByCompetition> {
                return listOf(
                        MatchesByCompetition("competition1", emptyList()),
                        MatchesByCompetition("competition2", emptyList())
                )
            }
        }

        return OddsProviders(listOf(p1, p2))
    }
}