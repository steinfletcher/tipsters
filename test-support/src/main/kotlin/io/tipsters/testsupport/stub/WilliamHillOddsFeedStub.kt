package io.tipsters.testsupport.stub

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.google.common.io.Resources.getResource
import com.google.common.io.Resources.toString
import java.util.concurrent.Executors
import kotlin.text.Charsets.UTF_8

val server = {
    val pafApiStub = WilliamHillOddsFeedStub(19002)
    pafApiStub.start()
    pafApiStub.willReturnTheOddsXMLFeed()
}

fun main(args: Array<String>) {
    Executors.newSingleThreadExecutor().execute(server)
}

class WilliamHillOddsFeedStub constructor(val port: Int) {
    private val mockServer = WireMockServer(wireMockConfig().port(port))
    private lateinit var wireMock: WireMock

    fun start() {
        mockServer.start()
        wireMock = WireMock("localhost", mockServer.port())
        println("Running wiremock paf stub on port " + port)
    }

    fun stop() {
        mockServer.stop()
    }

    fun willReturnTheOddsXMLFeed() {
        val xmlResponse = toString(getResource("xml/uk_football_stream.xml"), UTF_8)

        val urlPath = "/openbet_cdn.*"
        wireMock.register(get(urlPathMatching(urlPath))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody(xmlResponse)))
    }
}