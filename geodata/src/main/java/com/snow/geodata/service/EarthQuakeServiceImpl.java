package com.snow.geodata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.repository.EarthQuakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class EarthQuakeServiceImpl {

    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&endtime";

    public EarthQuake earthQuakeList() {
        Mono<EarthQuake> response = WebClient.create()
                .mutate()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)).build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EarthQuake.class)
                .log();

        EarthQuake earthQuake = response.block();

        return earthQuake;
    }


}
