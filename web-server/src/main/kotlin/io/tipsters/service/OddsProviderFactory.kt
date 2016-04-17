package io.tipsters.service

import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.config.OddsProviders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OddsProviderFactory @Autowired constructor(private val oddsProviders: OddsProviders) {

    fun providersForCompetitions(competitions: Set<String>): List<OddsProvider> {
        return oddsProviders.providers.filter {
            provider -> provider.providesFor().union(competitions).any()
        }
    }
}