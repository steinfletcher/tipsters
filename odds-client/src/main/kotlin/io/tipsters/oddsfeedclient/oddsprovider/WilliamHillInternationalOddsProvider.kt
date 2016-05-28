package io.tipsters.oddsfeedclient.oddsprovider

import io.tipsters.oddsfeedclient.WilliamHillInternationalOdds
import okhttp3.ResponseBody
import retrofit2.Response

class WilliamHillInternationalOddsProvider constructor(private val oddsUkOdds: WilliamHillInternationalOdds) : WilliamHillOddsProviderTemplate() {
    companion object {
        val competitions = setOf(
                "Copa America",
                "International Friendlies",
                "Euro 2016"
        )
    }

    override fun clientRequest(): Response<ResponseBody> = oddsUkOdds.matches().execute()

    /**
     * The competitions this provider generates odds for
     */
    override fun providesCompetitions(): Set<String> = competitions
}
