package com.snow.geodata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.entity.Features;
import com.snow.geodata.entity.Properties;
import com.snow.geodata.service.EarthQuakeServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GeodataApplicationTests {

    EarthQuakeServiceImpl earthQuakeService;
    private MockWebServer mockWebServer;
    private MockResponse mockResponse;

    @BeforeEach
    public void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        EarthQuake mockEarthQuake = new EarthQuake();
        mockEarthQuake.setType("FeatureCollection");
        Features features = new Features();
        features.setType("Feature");

        Properties properties = new Properties();
        properties.setMag("1.61");
        properties.setPlace("6km S of Brawley, CA");
        properties.setTime("1668096151270");
        properties.setTitle("M 1.6 - 6km S of Brawley, CA");

        features.setProperties(properties);
        mockEarthQuake.setFeatures(Collections.singletonList(features));

    }

    @Test
    void testEarthQuakeList() throws IOException {

        mockWebServer.enqueue(
                new MockResponse().setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("type\t\"FeatureCollection\"\n" +
                                "features\t\n" +
                                "0\t\n" +
                                "id\t\"ci40130727\"\n" +
                                "type\t\"Feature\"\n" +
                                "properties\t\n" +
                                "mag\t\"1.61\"\n" +
                                "place\t\"6km S of Brawley, CA\"\n" +
                                "time\t\"1668096151270\"\n" +
                                "updated\t\"1668096368076\"\n" +
                                "tsunami\t\"0\"\n" +
                                "magType\t\"ml\"\n" +
                                "type\t\"earthquake\"\n" +
                                "title\t\"M 1.6 - 6km S of Brawley, CA\"\n" +
                                "geometry\t\n" +
                                "type\t\"Point\"\n" +
                                "coordinates\t\n" +
                                "0\t\"-115.5263333\"\n" +
                                "1\t\"32.9258333\"\n" +
                                "2\t\"9.91\"")
        );

        mockWebServer.start();
    }
}
