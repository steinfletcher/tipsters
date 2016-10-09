package io.tipsters.uiapi.client.williamhill;

import java.util.List;

public class OddsProviders {
  private final List<OddsProvider> providers;

  public OddsProviders(List<OddsProvider> providers) {
    this.providers = providers;
  }

  public List<OddsProvider> getProviders() {
    return providers;
  }
}
