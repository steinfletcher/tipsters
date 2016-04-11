package io.tipsters.controller

import io.tipsters.service.CountryService
import io.tipsters.service.data.CountryData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
class CountriesController @Autowired constructor(private val countryService: CountryService) {

    @RequestMapping(method = arrayOf(GET))
    fun countries(@RequestParam(name = "limit", defaultValue = "10") limit: Int): List<CountryData> =
            countryService.countriesOrderedByPriority(limit)
}
