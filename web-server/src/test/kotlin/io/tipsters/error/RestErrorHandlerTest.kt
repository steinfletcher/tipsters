package io.tipsters.error

import org.junit.Assert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as eq

class RestErrorHandlerTest {

    private val underTest = ErrorMapper()

    @Test
    fun mapsTeamNotFoundError() {
        val error = OddsApiError("Some error")

        val errorResponse = underTest.handleException(error)

        assertThat(errorResponse.message, eq("Some error"))
        assertThat(errorResponse.type, eq("OddsApiError"))
    }
}