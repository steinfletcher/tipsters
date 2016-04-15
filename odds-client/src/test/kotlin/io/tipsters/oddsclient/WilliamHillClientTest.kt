package io.tipsters.oddsclient

import io.tipsters.oddsfeedclient.WilliamHillClient
import io.tipsters.oddsfeedclient.parser.WillHillOddsFeedParser
import io.tipsters.testsupport.stub.WilliamHillOddsFeedStub
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import org.hamcrest.CoreMatchers.`is` as eq

class WilliamHillClientTest {
    val port = 19002
    val oddsApiStub = WilliamHillOddsFeedStub(port)
    val httpClientBuilder = Retrofit.Builder()
            .baseUrl("http://localhost:$port/")
            .build();

    val underTest = httpClientBuilder.create(WilliamHillClient::class.java)

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
        val matches = underTest.matches().execute()

        val competitions = WillHillOddsFeedParser(matches.body().byteStream()).parse()

        assertThat(competitions[0].name, eq("Scottish Championship"))
        assertThat(competitions[0].matches[0].home, eq("Hibernian"))
        assertThat(competitions[0].matches[0].away, eq("Falkirk"))
    }
}
