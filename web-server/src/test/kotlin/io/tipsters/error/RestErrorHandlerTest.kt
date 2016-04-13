package io.tipsters.error

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class RestErrorHandlerTest {

    private val underTest = ErrorMapper()

    @Test
    fun mapsTeamNotFoundError() {
        val notFoundError = OddsApiError("Team ABC not found")

        val errorResponse = underTest.handleException(notFoundError)

        assertThat(errorResponse.message, `is`("Team ABC not found"))
        assertThat(errorResponse.type, `is`("TeamNotFoundError"))
    }
}