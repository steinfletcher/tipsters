package io.tipsters.uiapi.client.williamhill;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static java.util.Arrays.asList;

@Configuration
public class OddsProviderConfig {

  @Bean
  public OddsProviders oddsProviders(Environment environment) {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(logging);

    Retrofit willHillClientBuilder = new Retrofit.Builder()
        .baseUrl(environment.getRequiredProperty("tipsters.willHillFeedUrl"))
        .client(httpClient.build())
        .build();

    OddsClient.Uk williamHillUkClient = willHillClientBuilder.create(OddsClient.Uk.class);
    UkOddsProvider williamHillUkProvider = new UkOddsProvider(williamHillUkClient);

    OddsClient.Europe williamHillEuroClient = willHillClientBuilder.create(OddsClient.Europe.class);
    EuropeOddsProvider williamHillEuroProvider = new EuropeOddsProvider(williamHillEuroClient);

    OddsClient.International williamHillInternationalClient = willHillClientBuilder.create(OddsClient.International.class);
    InternationalOddsProvider williamHillInternationalProvider = new InternationalOddsProvider(williamHillInternationalClient);

    return new OddsProviders(asList(williamHillUkProvider, williamHillEuroProvider, williamHillInternationalProvider));
  }
}
