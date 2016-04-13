package io.tipsters.oddsclient.discovery

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.SAXParserFactory

fun main(args: Array<String>) {
    val inputStream = ClassLoader.getSystemResourceAsStream("uk_football_stream.xml")
    val parser = WillHillOddsFeedParser(inputStream)

    val competitions = parser.parse()

    val leagues = competitions.competitions.map { c -> c.name }
    println(leagues.joinToString { s -> s })
}

class WillHillOddsFeedParser constructor(val inputStream: InputStream) {
    fun parse(): Competitions {
        val parserFactory = SAXParserFactory.newInstance()
        val parser = parserFactory.newSAXParser()
        val handler = CompetitionXmlStreamParser()
        parser.parse(inputStream, handler)
        return handler.odds
    }
}

internal class CompetitionXmlStreamParser : DefaultHandler() {
    lateinit var competition: Competition
    var match: Match? = null
    lateinit var bet: Bet
    lateinit var matches: MutableList<Match>
    lateinit var bets: MutableList<Bet>
    lateinit var content: String
    val competitions = mutableListOf<Competition>()
    val odds = Competitions(competitions)

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
}

data class Bet(val outcome: String, val oddsDecimal: Float, val odds: String)

data class Match(val home: String, val away: String, val betType: String, val bets: List<Bet>, val date: LocalDateTime, val url: String)

data class Competition(val name: String, val matches: List<Match>)

data class Competitions(val competitions: List<Competition>)