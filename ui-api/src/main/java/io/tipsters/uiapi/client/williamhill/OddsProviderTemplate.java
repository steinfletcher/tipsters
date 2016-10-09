package io.tipsters.uiapi.client.williamhill;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import io.tipsters.uiapi.dto.CompetitionMatches;
import io.tipsters.uiapi.web.OddsProviderError;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static java.util.stream.Collectors.toList;

public abstract class OddsProviderTemplate implements OddsProvider {
  @Override
  public List<CompetitionMatches> odds(Set<String> competitionNames, LocalDateTime matchStart, LocalDateTime matchEnd) {
    try {
      Response<ResponseBody> response = clientRequest();
      if (response.isSuccessful()) {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        OddsFeedSaxHandler handler = new OddsFeedSaxHandler(matchStart, matchEnd);
        parser.parse(response.body().byteStream(), handler);
        List<CompetitionMatches> matches = handler.getCompetitions();

        return matches.stream()
            .filter(competition -> competitionNames.contains(competition.getCompetition()))
            .collect(toList());
      } else {
        throw new OddsProviderError("Failed to retrieve odds from William Hill API");
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new OddsProviderError("Failed to parse Odds from william hill", e);
    }
  }

  abstract Response<ResponseBody> clientRequest() throws IOException;
}
