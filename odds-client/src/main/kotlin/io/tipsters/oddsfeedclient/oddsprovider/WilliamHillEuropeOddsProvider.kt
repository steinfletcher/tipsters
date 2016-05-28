package io.tipsters.oddsfeedclient.oddsprovider

import io.tipsters.oddsfeedclient.WilliamHillEuropeOdds
import okhttp3.ResponseBody
import retrofit2.Response

class WilliamHillEuropeOddsProvider constructor(private val oddsUkOdds: WilliamHillEuropeOdds) : WilliamHillOddsProviderTemplate() {
    companion object {
        val competitions = setOf(
                "German Bundesliga",
                "Spanish La Liga Primera",
                "Spanish Segunda Division Femenina",
                "Italian Serie A"
        )
    }

    override fun clientRequest(): Response<ResponseBody> = oddsUkOdds.matches().execute()

    /**
     * The competitions this provider generates odds for
     */
    override fun providesCompetitions(): Set<String> = competitions
}
