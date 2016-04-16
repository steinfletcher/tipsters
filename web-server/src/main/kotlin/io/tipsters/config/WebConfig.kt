package io.tipsters.config

import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit

@Configuration
@EnableCaching
open class WebConfig {

    @Bean
    open fun oddsClient(): WilliamHillUkOdds {
        val httpClientBuilder = Retrofit.Builder()
                .baseUrl("http://cachepricefeeds.williamhill.com/")
                .build();

        return httpClientBuilder.create(WilliamHillUkOdds::class.java)
    }
}