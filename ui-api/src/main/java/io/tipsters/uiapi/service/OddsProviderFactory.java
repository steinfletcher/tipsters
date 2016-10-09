package io.tipsters.uiapi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import io.tipsters.uiapi.client.williamhill.OddsProvider;
import io.tipsters.uiapi.client.williamhill.OddsProviders;

import static java.util.stream.Collectors.toList;

@Service
public class OddsProviderFactory {
  private final OddsProviders oddsProviders;

  public OddsProviderFactory(OddsProviders oddsProviders) {
    this.oddsProviders = oddsProviders;
  }

  public List<OddsProvider> providersForCompetitions(Set<String> competitions) {
    List<OddsProvider> providers = oddsProviders.getProviders();
    return providers.stream()
        .filter(provider -> provider.providesCompetitions().stream().anyMatch(competitions::contains))
        .collect(toList());
  }
}
