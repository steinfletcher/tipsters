package io.tipsters.service

import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.config.OddsProviders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Provides a mechanism to get a handle on all odds providers that support the
 * given competitions
 */
@Service
open class OddsProviderFactory @Autowired constructor(private val oddsProviders: OddsProviders) {

    fun providersForCompetitions(competitions: Set<String>): List<OddsProvider> {
        return oddsProviders.providers.filter {
            provider -> provider.providesCompetitions().any {
                competition -> competitions.contains(competition)
            }
        }
    }
}
