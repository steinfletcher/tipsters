package io.tipsters.oddsfeedclient.domain

import java.time.LocalDateTime

data class Bet(val outcome: String, val oddsDecimal: Float, val odds: String)

data class Match(val home: String, val away: String, val betType: String, val bets: List<Bet>, val date: LocalDateTime, val url: String)

data class Competition(val name: String, val matches: List<Match>)

data class Competitions(val competitions: List<Competition>)