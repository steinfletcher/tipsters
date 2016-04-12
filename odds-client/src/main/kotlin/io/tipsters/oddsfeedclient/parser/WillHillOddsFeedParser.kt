package io.tipsters.oddsfeedclient.parser

import io.tipsters.oddsfeedclient.domain.Bet
import io.tipsters.oddsfeedclient.domain.Competition
import io.tipsters.oddsfeedclient.domain.Competitions
import io.tipsters.oddsfeedclient.domain.Match
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.SAXParserFactory

/**
 * A parser to extract odds from William Hill XML response
 */
class WillHillOddsFeedParser constructor(val inputStream: InputStream) {
    fun parse(): Competitions {
        val parser = SAXParserFactory.newInstance().newSAXParser()

        val handler = OddsXmlStreamHandler()
        parser.parse(inputStream, handler)

        return handler.odds
    }
}

/**
 * Parses the odds XML data as a stream (rather than mapping to POJO).
 * This is more efficient as the entire DOM is not loading into memory.
 * Not thread safe (create an instance per invocation)
 */
internal class OddsXmlStreamHandler : DefaultHandler() {
    lateinit var matches: MutableList<Match>
    var match: Match? = null

    lateinit var bets: MutableList<Bet>
    lateinit var bet: Bet
    lateinit var content: String

    lateinit var competition: Competition
    val competitions = mutableListOf<Competition>()
    val odds = Competitions(competitions)

    /**
     * Event handler for when an xml element is started
     */
    override fun startElement(uri: String, localName: String, qName: String, attr: Attributes) {
        when (qName) {
            "type" -> {
                matches = mutableListOf()
                competition = Competition(attr.getValue("name"), matches)
            }
            "market" -> {
                bets = mutableListOf()
                match = extractMatchFromElement(attr)
            }
            "participant" -> {
                bet = Bet(attr.getValue("name"), attr.getValue("oddsDecimal").toFloat(), attr.getValue("odds"))
            }
            else -> {
            }
        }
    }

    /**
     * Event handler for when an xml element is completed
     */
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when (qName) {
            "type" -> competitions.add(competition)
            "market" -> if (match != null) matches.add(match!!)
            "participant" -> bets.add(bet)
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        content = java.lang.String.copyValueOf(ch, start, length)
    }

    private fun extractMatchFromElement(attr: Attributes): Match? {
        val nameAttribute = parseName(attr.getValue("name"))
        val betType = nameAttribute.second
        if (betType == "Match Betting") {
            return Match(
                    home = nameAttribute.first[0],
                    away = nameAttribute.first[1],
                    betType = nameAttribute.second,
                    bets = bets,
                    date = parseDateTime(attr.getValue("date"), attr.getValue("time")),
                    url = attr.getValue("url"))
        } else {
            return null
        }
    }

    private fun parseName(title: String): Pair<List<String>, String> {
        val parts = title.split(" - ")
        val teams: List<String> = parts[0].split(" v ")
        return Pair(teams, parts[1])
    }

    private fun parseDateTime(date: String, time: String) = LocalDateTime.parse("${date}T$time", DateTimeFormatter.ISO_DATE_TIME)
}
