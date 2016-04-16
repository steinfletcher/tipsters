package io.tipsters.oddsfeedclient.oddsprovider

import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import okhttp3.ResponseBody
import retrofit2.Response

class WilliamHillUkOddsProvider constructor(private val oddsUkOdds: WilliamHillUkOdds) : WilliamHillOddsProvider() {

    override fun clientRequest(): Response<ResponseBody> = oddsUkOdds.matches().execute()

    /**
     * The competitions this provider generates odds for
     */
    override fun providesFor(): Set<String> {
        return setOf(
                "English Premier League",
                "English Premier League",
                "English FA Cup",
                "English Championship",
                "English League 1",
                "English League 2",
                "English National League",
                "Scottish Premiership",
                "William Hill Scottish Cup",
                "Scottish Championship",
                "Scottish League One",
                "Scottish League Two",
                "Welsh First Division"
        )
    }
}
