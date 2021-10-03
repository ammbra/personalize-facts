package org.acme.experiments.external;

import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import javax.ws.rs.core.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockFacts implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

//        var cats = "[{\"type\": \"cat\",\"source\": \"user\"}, {\"type\": \"cat\",\"source\": \"user\"}]";
        var cats = """
                [
                   {
                      "type":"cat",
                      "source":"user"
                   },
                   {
                      "type":"cat",
                      "source":"user"
                   }
                ]
                """;
        var cat = """
                {
                      "type":"cat",
                      "source":"user"
                   }
                """;

        stubFor(get(urlEqualTo("/facts?animal_type=cat"))
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                            .withBody(
                                                    cats
                                            )));

        stubFor(get(urlEqualTo("/facts/random?animal_type=cat&amount=2"))
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                            .withBody(
                                                    cats
                                            )));
        stubFor(get(urlEqualTo("/facts/591f98703b90f7150a19c125"))
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                            .withBody(
                                                    cat
                                            )));
        stubFor(get(urlEqualTo("/facts/588e746706ac2b00110e59ff"))
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                            .withBody(
                                                    cat
                                            )));
        stubFor(get(urlEqualTo("/facts/5887e1d85c873e0011036889"))
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                            .withBody(
                                                    cat
                                            )));
        stubFor(get(urlMatching(".*")).atPriority(10).willReturn(aResponse().proxiedFrom("https://cat-fact.herokuapp.com")));

        return Collections.singletonMap("org.acme.experiments.external.FactsService/mp-rest/url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}