package io.tipsters.testsupport;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.io.Resources.getResource;

public class WilliamHillOddsFeedStub {

  private final WireMockServer wireMockServer;
  private final int port;
  private WireMock wireMock;

  public WilliamHillOddsFeedStub(int port) {
    this.port = port;
    wireMockServer = new WireMockServer(wireMockConfig().port(port));
  }

  public void start() {
    wireMockServer.start();
    wireMock = new WireMock("localhost", wireMockServer.port());
    System.out.println("Running wiremock stub on port " + port);
  }

  public void stop() {
    wireMockServer.stop();
  }

  public void willReturnTheOddsXMLFeed() throws IOException {
    String xmlResponse = Resources.toString(getResource("xml/uk_football_stream.xml"), Charsets.UTF_8);
    String urlPath = "/openbet_cdn.*";

    wireMock.register(get(urlPathMatching(urlPath))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/xml")
            .withBody(xmlResponse)));
  }
}
