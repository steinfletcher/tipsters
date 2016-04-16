package io.tipsters.oddsclient.parser

import io.tipsters.oddsfeedclient.parser.WillHillOddsFeedParser
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.LocalDateTime
import javax.xml.parsers.SAXParserFactory
import org.hamcrest.CoreMatchers.`is` as eq

class WillHillOddsFeedParserTest {
    @Test
    fun parsesTheOddsFeedStream() {
        val inputStream = ClassLoader.getSystemResourceAsStream("euro_football_stream.xml")
        val parser = SAXParserFactory.newInstance().newSAXParser()
        val handler = WillHillOddsFeedParser()
        parser.parse(inputStream, handler)

        val firstCompetition = handler.competitions[0]
        assertThat(firstCompetition.competition, eq("Austrian Bundesliga"))

        val firstMatch = firstCompetition.matches[0]
        assertThat(firstMatch.home, eq("Salzburg"))
        assertThat(firstMatch.away, eq("Ried"))
        assertThat(firstMatch.betType, eq("Match Betting"))
        assertThat(firstMatch.date, equalTo(LocalDateTime.parse("2016-04-16T15:00:00")))

        val firstBet = firstMatch.bets[0]
        assertThat(firstBet.outcome, eq("Salzburg"))
        assertThat(firstBet.odds, eq("2/7"))
        assertThat(firstBet.oddsDecimal, eq(1.29f))
    }
}
