package io.tipsters.oddsclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import io.tipsters.testsupport.WilliamHillOddsFeedStub;
import io.tipsters.uiapi.client.williamhill.OddsClient;
import io.tipsters.uiapi.client.williamhill.UkOddsProvider;
import io.tipsters.uiapi.dto.CompetitionMatches;
import retrofit2.Retrofit;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class WilliamHillClientTest {
    private final int port = 19002;
    private final WilliamHillOddsFeedStub oddsApiStub = new WilliamHillOddsFeedStub(port);
    private final Retrofit httpClientBuilder = new Retrofit.Builder()
            .baseUrl("http://localhost:" + port)
            .build();

    @Before
    public void setUp() {
        oddsApiStub.start();
    }

    @After
    public void tearDown() {
        oddsApiStub.stop();
    }

    @Test
    public void returnsTheOddsFeed() throws IOException {
        OddsClient.Uk underTest = httpClientBuilder.create(OddsClient.Uk.class);

        oddsApiStub.willReturnTheOddsXMLFeed();

        UkOddsProvider oddsProvider = new UkOddsProvider(underTest);
        List<CompetitionMatches> competitions = oddsProvider.odds(newHashSet("Scottish Premiership"), LocalDateTime.MIN, LocalDateTime.MAX);

        assertThat(competitions.get(0).getCompetition()).isEqualTo("Scottish Premiership");
        assertThat(competitions.get(0).getMatches().get(0).getHome()).isEqualTo("Inverness");
        assertThat(competitions.get(0).getMatches().get(0).getAway()).isEqualTo("Hearts");
    }
}
