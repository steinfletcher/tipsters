package io.tipsters.oddsclient.parser;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import io.tipsters.uiapi.client.williamhill.OddsFeedSaxHandler;
import io.tipsters.uiapi.dto.Bet;
import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.dto.Match;

import static org.assertj.core.api.Assertions.assertThat;

public class WillHillOddsFeedParserTest {

  @Test
  public void parsesTheOddsFeedStream() throws IOException, SAXException, ParserConfigurationException {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("xml/euro_football_stream.xml");
    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
    OddsFeedSaxHandler handler = new OddsFeedSaxHandler(LocalDateTime.MIN, LocalDateTime.MAX);
    parser.parse(inputStream, handler);

    CompetitionMatches firstCompetition = handler.getCompetitions().get(0);
    assertThat(firstCompetition.getCompetition()).isEqualTo("Austrian Bundesliga");

    Match firstMatch = firstCompetition.getMatches().get(0);
    assertThat(firstMatch.getHome()).isEqualTo("Salzburg");
    assertThat(firstMatch.getAway()).isEqualTo("Ried");
    assertThat(firstMatch.getBetType()).isEqualTo("Match Betting");
    assertThat(firstMatch.getDate()).isEqualTo(LocalDateTime.parse("2016-04-16T15:00:00"));

    Bet firstBet = firstMatch.getBets().get(0);
    assertThat(firstBet.getOutcome()).isEqualTo("Salzburg");
    assertThat(firstBet.getOdds()).isEqualTo("2/7");
    assertThat(firstBet.getOddsDecimal()).isEqualTo(1.29f);
  }
}
