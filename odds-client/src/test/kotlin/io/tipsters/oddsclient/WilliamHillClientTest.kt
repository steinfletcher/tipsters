package io.tipsters.oddsclient

import io.tipsters.common.data.MatchesByCompetition
import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import io.tipsters.oddsfeedclient.oddsprovider.WilliamHillUkOddsProvider
import io.tipsters.testsupport.stub.WilliamHillOddsFeedStub
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.time.LocalDateTime
import org.hamcrest.CoreMatchers.`is` as eq

class WilliamHillClientTest {
    val port = 19002
    val oddsApiStub = WilliamHillOddsFeedStub(port)
    val httpClientBuilder = Retrofit.Builder()
            .baseUrl("http://localhost:$port/")
            .build();

    val underTest = httpClientBuilder.create(WilliamHillUkOdds::class.java)

    @Before
    fun setUp() {
        oddsApiStub.start()
    }

    @After
    fun tearDown() {
        oddsApiStub.stop()
    }

    @Test
    fun returnsTheOddsFeed() {
        oddsApiStub.willReturnTheOddsXMLFeed()

        val oddsProvider = WilliamHillUkOddsProvider(underTest)
        val competitions: List<MatchesByCompetition> = oddsProvider.odds(setOf("Scottish Championship"), LocalDateTime.MIN, LocalDateTime.MAX)

        assertThat(competitions[0].competition, eq("Scottish Championship"))
        assertThat(competitions[0].matches[0].home, eq("Hibernian"))
        assertThat(competitions[0].matches[0].away, eq("Falkirk"))
    }
}
