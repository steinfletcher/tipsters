package io.tipsters.oddsfeedclient.oddsprovider

import io.tipsters.common.error.OddsProviderError
import io.tipsters.oddsfeedclient.WilliamHillUkOdds
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.time.LocalDateTime

class WilliamHillOddsProviderTemplateTest {
    lateinit var httpClient: WilliamHillUkOdds
    lateinit var underTest: WilliamHillUkOddsProvider

    @Before
    fun setUp() {
        httpClient = Mockito.mock(WilliamHillUkOdds::class.java)
        underTest = WilliamHillUkOddsProvider(httpClient)
    }

    @Test(expected = OddsProviderError::class)
    fun throwsOddsApiErrorIfHttpRequestThrows() {
        val competitions = setOf("someId")
        val httpMockResponse = mock(Call::class.java)
        given(httpMockResponse.execute()).willThrow(IOException("Error"))

        underTest.odds(competitions, LocalDateTime.MIN, LocalDateTime.MAX)
    }

    @Test(expected = OddsProviderError::class)
    fun throwsOddsApiErrorIfResponseIsNotSuccess() {
        val competitions = setOf("someId")
        val httpMockResponse = mock(Call::class.java)
        given(httpMockResponse.execute()).willReturn(
                Response.error(500, ResponseBody.create(MediaType.parse("application/xml"), "")))

        underTest.odds(competitions, LocalDateTime.MIN, LocalDateTime.MAX)
    }
}