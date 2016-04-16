package io.tipsters.repository

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.util.*

@SqlGroup(Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = arrayOf("classpath:db/create.sql", "classpath:db/insert.sql")))
class CompetitionRepositoryTest : RepositoryTest() {

    @Autowired
    lateinit var underTest: CompetitionRepository

    @Test
    fun returnsTheCompetitionNamesForTheGivenIds() {
        val ids = listOf<UUID>(
                UUID.fromString("05c6dd65-8141-4093-827a-b05372bcbbe5"),
                UUID.fromString("5070aa50-e5fe-4f3b-84a6-7b304c5533fb"))

        val competitions = underTest.findByIdIn(ids.map { id -> id.toString() })

        assertThat(competitions.size, `is`(2))
        assertThat(competitions, hasItem("English Premier League"))
        assertThat(competitions, hasItem("English FA Cup"))
    }
}