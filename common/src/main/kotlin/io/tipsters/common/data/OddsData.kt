package io.tipsters.common.data

import java.time.LocalDateTime

data class MatchesByCompetition(val competition: String, val matches: List<Match>)

data class Bet(val outcome: String,
               val oddsDecimal: Float,
               val odds: String)

data class Match(val home: String,
                 val away: String,
                 val betType: String,
                 val bets: List<Bet>,
                 val date: LocalDateTime,
                 val url: String)