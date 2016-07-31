package io.tipsters.oddsfeedclient.parser

import io.tipsters.common.data.Bet
import io.tipsters.common.data.Match
import io.tipsters.common.data.MatchesByCompetition
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Parses the odds XML data as a stream (rather than mapping to POJO).
 * This is more efficient as the entire DOM is not loaded into memory.
 * Not thread safe (create an instance per invocation)
 */
internal class WillHillOddsFeedSaxHandler(val start: LocalDateTime, val end: LocalDateTime) : DefaultHandler() {
    private lateinit var matches: MutableList<Match>
    private var match: Match? = null
    private lateinit var bets: MutableList<Bet>
    private lateinit var bet: Bet
    private lateinit var content: String

    private lateinit var competition: MatchesByCompetition

    val competitions = mutableListOf<MatchesByCompetition>()

    /**
     * Event handler for when an xml element is started
     */
    override fun startElement(uri: String, localName: String, qName: String, attr: Attributes) {
        when (qName) {
            "type" -> {
                matches = mutableListOf()
                val competitionName = attr.getValue("name")
                competition = MatchesByCompetition(competitionName, matches)
            }
            "market" -> {
                bets = mutableListOf()
                match = extractMatchFromElement(attr)
            }
            "participant" -> {
                bet = Bet(outcome = attr.getValue("name"),
                        oddsDecimal = attr.getValue("oddsDecimal").toFloat(),
                        odds = attr.getValue("odds"))
            }
            else -> {
                // do nothing
            }
        }
    }

    /**
     * Event handler for when an xml element is completed
     */
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when (qName) {
            "type" -> competitions.add(competition)
            "market" -> if (match != null && isWithinPeriod(match as Match)) matches.add(match!!)
            "participant" -> bets.add(bet)
        }
    }

    private fun isWithinPeriod(match: Match): Boolean {
        return match.date.isBefore(end) && match.date.isAfter(start)
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        content = java.lang.String.copyValueOf(ch, start, length)
    }

    private fun extractMatchFromElement(attr: Attributes): Match? {
        val nameAttribute = parseName(attr.getValue("name"))
        val betType = nameAttribute.second
        if (betType == "Match Betting") {
            if (nameAttribute.first.size != 2) {
                return null
            } else {
                return Match(
                        home = nameAttribute.first[0],
                        away = nameAttribute.first[1],
                        betType = nameAttribute.second,
                        bets = bets,
                        date = parseDateTime(attr.getValue("date"), attr.getValue("time")),
                        url = attr.getValue("url"))
            }
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
