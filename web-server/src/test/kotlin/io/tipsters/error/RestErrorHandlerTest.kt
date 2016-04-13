package io.tipsters.error

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class RestErrorHandlerTest {

    private val underTest = ErrorMapper()

    @Test
    fun mapsTeamNotFoundError() {
        val error = OddsApiError("Some error")

        val errorResponse = underTest.handleException(error)

        assertThat(errorResponse.message, `is`("Some error"))
        assertThat(errorResponse.type, `is`("OddsApiError"))
    }
}