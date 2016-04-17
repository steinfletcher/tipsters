package io.tipsters.config

import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.oddsfeedclient.WilliamHillEuropeOdds
import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import io.tipsters.oddsfeedclient.oddsprovider.WilliamHillEuropeOddsProvider
import io.tipsters.oddsfeedclient.oddsprovider.WilliamHillUkOddsProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import retrofit2.Retrofit

@Configuration
open class OddsProviderConfig {

    @Bean
    open fun oddsProviders(environment: Environment): OddsProviders {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder();
        httpClient.addInterceptor(logging)

        val willHillClientBuilder = Retrofit.Builder()
                .baseUrl(environment.getRequiredProperty("tipsters.willHillFeedUrl"))
                .client(httpClient.build())
                .build();

        val williamHillUkClient = willHillClientBuilder.create(WilliamHillUkOdds::class.java)
        val williamHillUkProvider = WilliamHillUkOddsProvider(williamHillUkClient)

        val williamHillEuroClient = willHillClientBuilder.create(WilliamHillEuropeOdds::class.java)
        val williamHillEuroProvider = WilliamHillEuropeOddsProvider(williamHillEuroClient)

        return OddsProviders(listOf(williamHillUkProvider, williamHillEuroProvider))
    }
}

open class OddsProviders(val providers: List<OddsProvider>)
