package io.tipsters.service

import io.tipsters.domain.Country
import io.tipsters.repository.CountriesRepository
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.hamcrest.CoreMatchers.`is` as eq

class CountryServiceTest {

    lateinit var underTest: CountryService
    lateinit var countryRepository: CountriesRepository

    @Before
    fun setUp() {
        countryRepository = mock(CountriesRepository::class.java)
        underTest = CountryService(countryRepository)
    }

    @Test
    fun returnsTheCountries() {
        given(countryRepository.findAll(any(Pageable::class.java)))
                .willReturn(PageImpl(listOf(Country(name = "England"), Country(name = "Japan"))))

        val countries = underTest.countriesOrderedByPriority(limit = 2)

        assertThat(countries[0].name, eq("England"))
        assertThat(countries[1].name, eq("Japan"))
    }
}
