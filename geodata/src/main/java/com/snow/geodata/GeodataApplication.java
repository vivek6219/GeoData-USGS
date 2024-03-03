package com.snow.geodata;

import com.snow.geodata.entity.EarthQuake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class GeodataApplication {

	private static final Logger log = LoggerFactory.getLogger(GeodataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GeodataApplication.class, args);
	}

	@Bean
	public WebClient webClient(){
		return WebClient.create();
	}

}
