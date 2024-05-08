package com.snow.geodata.service;

import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.entity.Features;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EarthQuakeServiceImpl {

    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&endtime";
    String urlForYesterday = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

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

    public EarthQuake earthquakeFromYesterday(){
        Mono<EarthQuake> response = WebClient.create()
                .mutate()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
                .get()
                .uri(urlForYesterday)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EarthQuake.class)
                .log();

        return response.block();
    }
}
