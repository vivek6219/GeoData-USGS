package com.snow.geodata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.entity.Features;
import com.snow.geodata.repository.EarthQuakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class EarthQuakeServiceImpl {

    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&endtime";

    public EarthQuake earthQuakeList() {
        Mono<EarthQuake> response = WebClient.create()
                .mutate()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EarthQuake.class)
                .log();

        EarthQuake earthQuake = response.block();

        return earthQuake;
    }


    /*
     *Will return all earthquakes of the given magnitude
     * */
    public List<String> returnEarthQuakeByMagnitude(String magnitude) {
        Mono<EarthQuake> response = WebClient.create()
                .mutate()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EarthQuake.class)
                .log();

        //how do i map a specific city and state to a magnitude?
        //when their is no specified city.
        //can maybe fetch

        //sorted (Binary)? Tree with list of locations as property

        List<Features> featuresList = response.block().getFeatures();
        List<String> magnitudes = new ArrayList<>();
        for (Features features : featuresList) {
            if (features != null && features.getProperties() != null
                    && features.getProperties().getMag() != null && !features.getProperties().getMag().isEmpty()
                    && features.getProperties().getPlace() != null && !features.getProperties().getPlace().isEmpty()
                    && Objects.equals(features.getProperties().getMag(), magnitude)) {
                magnitudes.add(features.getProperties().getPlace());
            }
        }
        return magnitudes;
    }


    /*
     * Will get all data based on location. Might think about passing in location name
     * and then do a search based on that location using EarthQuake.features.place
     * */
    public EarthQuake earthQuakesByLocation() {
        Mono<EarthQuake> response = WebClient.create()
                .mutate()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
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
