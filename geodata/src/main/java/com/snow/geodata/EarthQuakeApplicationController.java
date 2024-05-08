package com.snow.geodata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.entity.Features;
import com.snow.geodata.entity.Properties;
import com.snow.geodata.service.EarthQuakeServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class EarthQuakeApplicationController {

    EarthQuakeServiceImpl earthQuakeService;

    public EarthQuakeApplicationController(EarthQuakeServiceImpl earthQuakeService) {
        this.earthQuakeService = earthQuakeService;
    }

//    @RequestMapping("/")
//    public String index(Model model){
//        return "../resources/templates/start_page.html";
//    }

    @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("earthquakes")
    public EarthQuake getEarthQuakeData() {
        return earthQuakeService.earthQuakeList();
    }

    @GetMapping("earthquakes/location")
    public EarthQuake getEarthQuakeByLocationName() {
        return earthQuakeService.earthQuakesByLocation();
    }

    @GetMapping("earthquakes/magnitude/{mag}")
    public List<String> getEarthQuakeByMagnitude(@PathVariable("mag") String magnitude) {
        return earthQuakeService.returnEarthQuakeByMagnitude(magnitude);
    }

    @GetMapping("earthquakes/yesterday")
    public EarthQuake getEarthquakesFromYesterday(){
        return earthQuakeService.earthquakeFromYesterday();
    }
}
