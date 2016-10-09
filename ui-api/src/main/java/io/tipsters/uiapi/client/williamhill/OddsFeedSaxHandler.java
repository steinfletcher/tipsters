package io.tipsters.uiapi.client.williamhill;

import org.flywaydb.core.internal.util.Pair;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import io.tipsters.uiapi.dto.Bet;
import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.dto.Match;

public class OddsFeedSaxHandler extends DefaultHandler {
  private final LocalDateTime start;
  private final LocalDateTime end;
  private List<Match> matches;
  private Match match;
  private List<Bet> bets;
  private Bet bet;
  private String content;
  private CompetitionMatches competition;
  private List<CompetitionMatches> competitions = new ArrayList<>();

  public OddsFeedSaxHandler(LocalDateTime start, LocalDateTime end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    switch (qName) {
      case "type":
        matches = new ArrayList<>();
        String name = attributes.getValue("name");
        competition = new CompetitionMatches.Builder()
            .withCompetition(name)
            .withMatches(matches)
            .build();
        break;
      case "market":
        bets = new ArrayList<>();
        match = extractMatchFromElement(attributes);
        break;
      case "participant":
        bet = new Bet.Builder()
            .withOutcome(attributes.getValue("name"))
            .withOdds(attributes.getValue("odds"))
            .withOddsDecimal(Float.valueOf(attributes.getValue("oddsDecimal")))
            .build();
        break;
      default:
        break;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    switch (qName) {
      case "type":
        competitions.add(competition);
        break;
      case "market":
        if (match != null && isWithinPeriod(match)) {
          matches.add(match);
        }
        break;
      case "participant":
        bets.add(bet);
        break;
      default:
        break;
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    content = String.copyValueOf(ch, start, length);
  }

  private boolean isWithinPeriod(Match match) {
    return match.getDate().isBefore(end) && match.getDate().isAfter(start);
  }

  private Match extractMatchFromElement(Attributes attr) {
    Pair<String[], String> nameAttribute = parseName(attr.getValue("name"));
    String betType = nameAttribute.getRight();
    if ("90 Minutes".equals(betType) || "Match Betting".equals(betType)) {
      if (nameAttribute.getLeft().length != 2) {
        return null;
      } else {
        return new Match.Builder()
            .withHome(nameAttribute.getLeft()[0])
            .withAway(nameAttribute.getLeft()[1])
            .withBets(bets)
            .withBetType(betType)
            .withDate(parseDateTime(attr.getValue("date"), attr.getValue("time")))
            .withUrl(attr.getValue("url"))
            .build();
      }
    } else {
      return null;
    }
  }

  private Pair<String[], String> parseName(String title) {
    String[] parts = title.split(" - ");
    String[] teams = parts[0].split(" v ");
    return Pair.of(teams, parts[1]);
  }

  private LocalDateTime parseDateTime(String date, String time) {
    return LocalDateTime.parse(date + "T" + time, DateTimeFormatter.ISO_DATE_TIME);
  }

  public List<CompetitionMatches> getCompetitions() {
    return competitions;
  }
}
