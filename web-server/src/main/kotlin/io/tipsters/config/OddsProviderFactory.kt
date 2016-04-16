package io.tipsters.config

import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.oddsfeedclient.WilliamHillEuropeOdds
import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import io.tipsters.oddsfeedclient.oddsprovider.WilliamHillEuropeOddsProvider
import io.tipsters.oddsfeedclient.oddsprovider.WilliamHillUkOddsProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import retrofit2.Retrofit

@Component
class OddsProviderFactory @Autowired constructor(private val oddsProviders: OddsProviders) {

    fun providersForCompetitions(competitions: Set<String>): List<OddsProvider> {
        return oddsProviders.providers.filter {
            provider -> provider.providesFor().union(competitions).any()
        }
    }
}

@Configuration
open class OddsProviderConfig {

    @Bean
    open fun oddsProviders(): OddsProviders {
        val willHillClientBuilder = Retrofit.Builder()
                .baseUrl("http://cachepricefeeds.williamhill.com/")
                .build();

        val williamHillUkClient = willHillClientBuilder.create(WilliamHillUkOdds::class.java)
        val williamHillUkProvider = WilliamHillUkOddsProvider(williamHillUkClient)

        val williamHillEuroClient = willHillClientBuilder.create(WilliamHillEuropeOdds::class.java)
        val williamHillEuroProvider = WilliamHillEuropeOddsProvider(williamHillEuroClient)

        return OddsProviders(listOf(williamHillUkProvider, williamHillEuroProvider))
    }
}

data class OddsProviders(val providers: List<OddsProvider>)

