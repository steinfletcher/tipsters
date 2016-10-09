package io.tipsters.uiapi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.tipsters.uiapi.dto.CountryData;
import io.tipsters.uiapi.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountriesController {

  private final CountryService countryService;

  public CountriesController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  List<CountryData> countries(@RequestParam(name = "limit", defaultValue = "10") int limit) {
    return countryService.countriesOrderedByPriority(limit);
  }
}
