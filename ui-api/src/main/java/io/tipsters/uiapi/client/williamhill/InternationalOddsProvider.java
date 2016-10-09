package io.tipsters.uiapi.client.williamhill;

import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class InternationalOddsProvider extends OddsProviderTemplate {
  private static final Set<String> competitions = ImmutableSet.of(
      "Copa America",
      "International Friendlies",
      "Euro 2016"
  );
  private final OddsClient.International oddsClient;

  public InternationalOddsProvider(OddsClient.International oddsClient) {
    this.oddsClient = oddsClient;
  }

  @Override
  public Set<String> providesCompetitions() {
    return competitions;
  }

  @Override
  Response<ResponseBody> clientRequest() throws IOException {
    return oddsClient.matches().execute();
  }
}
