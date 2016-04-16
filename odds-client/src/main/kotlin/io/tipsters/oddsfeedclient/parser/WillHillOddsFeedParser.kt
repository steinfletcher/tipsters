package io.tipsters.oddsfeedclient.parser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Parses the odds XML data as a stream (rather than mapping to POJO).
 * This is more efficient as the entire DOM is not loaded into memory.
 * Not thread safe (create an instance per invocation)
 */
internal class WillHillOddsFeedParser : DefaultHandler() {

    private lateinit var matches: MutableList<io.tipsters.common.data.Match>
    private var match: io.tipsters.common.data.Match? = null
    private lateinit var bets: MutableList<io.tipsters.common.data.Bet>
    private lateinit var bet: io.tipsters.common.data.Bet
    private lateinit var content: String

    private lateinit var competition: io.tipsters.common.data.MatchesByCompetition

    val competitions = mutableListOf<io.tipsters.common.data.MatchesByCompetition>()

    /**
     * Event handler for when an xml element is started
     */
    override fun startElement(uri: String, localName: String, qName: String, attr: Attributes) {
        when (qName) {
            "type" -> {
                matches = mutableListOf()
                competition = io.tipsters.common.data.MatchesByCompetition(attr.getValue("name"), matches)
            }
            "market" -> {
                bets = mutableListOf()
                match = extractMatchFromElement(attr)
            }
            "participant" -> {
                bet = io.tipsters.common.data.Bet(attr.getValue("name"), attr.getValue("oddsDecimal").toFloat(), attr.getValue("odds"))
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

    private fun extractMatchFromElement(attr: Attributes): io.tipsters.common.data.Match? {
        val nameAttribute = parseName(attr.getValue("name"))
        val betType = nameAttribute.second
        if (betType == "Match Betting") {
            return io.tipsters.common.data.Match(
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
