package io.tipsters.uiapi.client.williamhill;

import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class UkOddsProvider extends OddsProviderTemplate {
  private static final Set<String> competitions = ImmutableSet.of(
      "English Premier League",
      "English Premier League",
      "English FA Cup",
      "English Championship",
      "English League 1",
      "English League 2",
      "English National League",
      "Scottish Premiership",
      "William Hill Scottish Cup",
      "Scottish Championship",
      "Scottish League One",
      "Scottish League Two",
      "Welsh First Division"
  );
  private final OddsClient.Uk oddsUk;

  public UkOddsProvider(OddsClient.Uk oddsUk) {
    this.oddsUk = oddsUk;
  }

  @Override
  public Set<String> providesCompetitions() {
    return competitions;
  }

  @Override
  Response<ResponseBody> clientRequest() throws IOException {
    return oddsUk.matches().execute();
  }
}
