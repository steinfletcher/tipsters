package io.tipsters.uiapi.client.williamhill;

import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class EuropeOddsProvider extends OddsProviderTemplate {
  private static final Set<String> competitions = ImmutableSet.of(
      "German Bundesliga",
      "Spanish La Liga Primera",
      "Spanish Segunda Division Femenina",
      "Italian Serie A"
  );
  private final OddsClient.Europe europeOddsClient;

  public EuropeOddsProvider(OddsClient.Europe odds) {
    this.europeOddsClient = odds;
  }

  @Override
  public Set<String> providesCompetitions() {
    return competitions;
  }

  @Override
  Response<ResponseBody> clientRequest() throws IOException {
    return europeOddsClient.matches().execute();
  }
}
