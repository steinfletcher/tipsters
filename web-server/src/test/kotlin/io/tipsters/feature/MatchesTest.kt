package io.tipsters.feature

import io.tipsters.controller.MatchesByCompetitions
import io.tipsters.testsupport.FeatureTest
import io.tipsters.testsupport.Json.Companion.jsonMapper
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID.fromString
import org.hamcrest.CoreMatchers.`is` as eq

@SqlGroup(Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = arrayOf("classpath:db/initdb.sql")))
class MatchesTest : FeatureTest() {
    val log = LoggerFactory.getLogger(MatchesTest::class.java)

    @Test
    fun returnsTheMatchesForTheGivenCompetitions() {
        oddsFeedStub.willReturnTheOddsXMLFeed()
        val englishPremierLeague = fromString("05c6dd65-8141-4093-827a-b05372bcbbe5")
        val requestBody = MatchesByCompetitions(setOf(englishPremierLeague))

        log.info(jsonMapper.writeValueAsString(requestBody))

        mockMvc.perform(post("/matches")
                .content(jsonMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$.length()", eq(1)))
                .andExpect(jsonPath("$[0].competition", eq("English Premier League")))
                .andExpect(jsonPath("$[0].matches[0].home", eq("Crystal Palace")))
                .andExpect(jsonPath("$[0].matches[0].away", eq("Everton")))
                .andExpect(jsonPath("$[0].matches[0].betType", eq("Match Betting")))
    }

    @Test
    fun returnsNotFoundIfCompetitionIdNotFound() {
        oddsFeedStub.willReturnTheOddsXMLFeed()
        val invalidCompetition = fromString("05c6dd65-8141-4093-827a-b05372bcaaaa")
        val requestBody = MatchesByCompetitions(setOf(invalidCompetition))

        log.info(jsonMapper.writeValueAsString(requestBody))

        mockMvc.perform(post("/matches")
                .content(jsonMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound)
                .andExpect(jsonPath("$.type", eq("CompetitionNotFoundError")))
                .andExpect(jsonPath("$.message", containsString("No competitions")))
    }

    @Test
    fun returnsBadRequestIfNoCompetitionsInRequest() {
        val requestBody = MatchesByCompetitions(emptySet())

        log.info(jsonMapper.writeValueAsString(requestBody))

        mockMvc.perform(post("/matches")
                .content(jsonMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest)
    }
}