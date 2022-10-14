package com.snow.geodata.service;

import com.snow.geodata.entity.EarthQuake;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EarthQuakeServiceImplTest {
    EarthQuakeServiceImpl earthQuakeService;
    EarthQuake earthQuake;
    WebClient webClient;

    @BeforeAll
    public void setup(){
        earthQuake = new EarthQuake();
        webClient = mock(WebClient.class);
        earthQuakeService = new EarthQuakeServiceImpl();
    }
//
//    public void testEarthquakeListReturnsMonoResponse(){
//        earthQuake = earthQuakeService.earthQuakeList();
//        when(webClient.get()).thenReturn();
//    }

}
