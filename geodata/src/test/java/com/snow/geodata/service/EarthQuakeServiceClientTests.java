package com.snow.geodata.service;

import com.snow.geodata.entity.EarthQuake;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;

@SpringBootTest
public class EarthQuakeServiceClientTests {

    private  final BasicJsonTester json = new BasicJsonTester(this.getClass());
    private MockWebServer mockWebServer;
    private EarthQuake earthQuake;
    private EarthQuakeServiceImpl earthQuakeService;

    @BeforeEach
    public void setup(){
        mockWebServer = new MockWebServer();
        earthQuakeService = new EarthQuakeServiceImpl();
        earthQuake = new EarthQuake();
    }

//    public void testEarthquakeListReturnsMonoResponse(){
//        earthQuake = earthQuakeService.earthQuakeList();
//        when(webClient.get()).thenReturn();
//    }

}
