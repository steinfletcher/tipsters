package io.tipsters.uiapi.client.williamhill;

import static java.util.stream.Collectors.toList;

import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.web.OddsProviderError;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import retrofit2.Response;

public abstract class OddsProviderTemplate implements OddsProvider {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private List<CompetitionMatches> lastSuccess = Collections.emptyList();

  @Override
  public List<CompetitionMatches> odds(Set<String> competitionNames, LocalDateTime matchStart, LocalDateTime matchEnd) {
    try {
      Response<ResponseBody> response = clientRequest();
      if (response.isSuccessful()) {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        OddsFeedSaxHandler handler = new OddsFeedSaxHandler(matchStart, matchEnd);
        parser.parse(response.body().byteStream(), handler);
        List<CompetitionMatches> matches = handler.getCompetitions();

        List<CompetitionMatches> competitionMatches = matches.stream()
            .filter(competition -> competitionNames.contains(competition.getCompetition()))
            .collect(toList());

        lastSuccess = competitionMatches;
        return competitionMatches;
      } else {
        throw new OddsProviderError("Failed to retrieve odds from William Hill API");
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      log.error("Failed to parse Odds from william hill. Returning cached response from last succes", e);
      return lastSuccess;
    }
  }

  abstract Response<ResponseBody> clientRequest() throws IOException;
}
