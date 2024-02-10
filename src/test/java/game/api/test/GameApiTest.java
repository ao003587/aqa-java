package game.api.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class GameApiTest {

    private WireMockServer wireMockServer;
    private OkHttpClient client;
    private String baseUrl;
    private final int port = 8089;

    @BeforeClass
    public void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(port));
        wireMockServer.start();
        client = new OkHttpClient.Builder()
                .build();
        baseUrl = String.format("http://localhost:%s",port);
    }

    @AfterClass
    public void shutDown() {
        wireMockServer.stop();
        client.connectionPool().evictAll();
    }

    @Test
    public void getSkillEndpointShouldReturnExpectedCodeWithExpectedDelay() {

        var expectedResponseCode = 242;
        var expectedDelay = 2577;
        var expectedDelayDelta = 100;

        WireMock.configureFor(port);
        WireMock.stubFor(WireMock.get("/character/skill/1")
                .willReturn(WireMock.aResponse()
                        .withStatus(expectedResponseCode)
                        .withHeader("cache-control", "no-store")
                        .withFixedDelay(expectedDelay)));

        try {
            var request = new Request.Builder()
                .url(String.format("%s/character/skill/1", baseUrl))
                .get()
                .build();
            try (var response = client.newCall(request).execute()) {
                Assert.assertEquals(response.code(), expectedResponseCode, "Response code is not as expected");
                Assert.assertEquals(response.receivedResponseAtMillis() - response.sentRequestAtMillis(), expectedDelay, expectedDelayDelta, "Response delay is not as expected");
            }
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
