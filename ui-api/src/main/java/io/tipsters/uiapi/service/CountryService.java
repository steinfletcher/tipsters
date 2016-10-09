package io.tipsters.uiapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import io.tipsters.uiapi.dto.CountryData;
import io.tipsters.uiapi.repository.CountryRepository;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class CountryService {
  private final CountryRepository countryRepository;

  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<CountryData> countriesOrderedByPriority(int limit) {
    return countryRepository.findAll(new PageRequest(0, limit, ASC, "rank")).getContent().stream()
        .map(CountryData::of)
        .collect(toList());
  }
}
