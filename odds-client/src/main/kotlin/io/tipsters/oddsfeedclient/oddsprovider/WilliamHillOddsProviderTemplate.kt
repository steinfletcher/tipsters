package io.tipsters.oddsfeedclient.oddsprovider

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.common.error.OddsProviderError
import io.tipsters.common.oddsprovider.OddsProvider
import io.tipsters.oddsfeedclient.parser.WillHillOddsFeedParser
import okhttp3.ResponseBody
import retrofit2.Response
import javax.xml.parsers.SAXParserFactory

/**
 * A parser to extract odds from William Hill XML response
 */
abstract class WilliamHillOddsProviderTemplate : OddsProvider {
    override fun odds(competitionNames: Set<String>): List<MatchesByCompetition> {
        val response = try {
            clientRequest()
        } catch (e: Exception) {
            throw OddsProviderError("Failed to retrieve odds from upstream", e)
        }
        if (response.isSuccessful) {
            val parser = SAXParserFactory.newInstance().newSAXParser()
            val handler = WillHillOddsFeedParser()
            parser.parse(response.body().byteStream(), handler)
            return handler.competitions.filter { competition -> competitionNames.contains(competition.competition) }
        } else {
            throw OddsProviderError("Failed to retrieve odds from William Hill API")
        }
    }

    abstract fun clientRequest(): Response<ResponseBody>
}