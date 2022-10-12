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
//    public EarthQuake returnEarthQuakeByMagnitude(){
//        Mono<EarthQuake> response = WebClient.create()
//                .mutate()
//                .codecs(configurer -> configurer
//                        .defaultCodecs()
//                        .maxInMemorySize(16 * 1024 * 1024))
//                .build()
//                .get()
//                .uri(url)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(EarthQuake.class)
//                .log();
//
//        //how do i map a specific city and state to a magnitude?
//        //when their is no specified city.
//        //can maybe fetch
//        Map<>
//
//    }



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
