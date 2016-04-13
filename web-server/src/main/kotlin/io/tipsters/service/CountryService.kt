package io.tipsters.service

import io.tipsters.repository.CountriesRepository
import io.tipsters.service.data.CountryData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Service

@Service
class CountryService @Autowired constructor(private val countryRepository: CountriesRepository) {

    fun countriesOrderedByPriority(limit: Int): List<CountryData> =
            countryRepository
                    .findAll(PageRequest(0, limit, ASC, "rank"))
                    .content.map { country -> CountryData.of(country) }
}
