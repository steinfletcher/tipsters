package io.tipsters.feature

import io.tipsters.testsupport.FeatureTest
import org.junit.Test
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.hamcrest.CoreMatchers.`is` as eq

@SqlGroup(Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = arrayOf("classpath:db/initdb.sql")))
class CountriesTest : FeatureTest() {

    @Test
    @Throws(Exception::class)
    fun shouldReturnTheCountries() {
        val endpoint = "/countries"

        mockMvc.perform(get(endpoint))
                .andDo(print())
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$[0].name", eq("England")))
                .andExpect(jsonPath("$[1].name", eq("Spain")))
                .andExpect(jsonPath("$[2].name", eq("Germany")))
    }
}
