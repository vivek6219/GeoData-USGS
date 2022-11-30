package com.snow.geodata;

import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.entity.Features;
import com.snow.geodata.service.EarthQuakeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EarthQuakeApplicationController {

    EarthQuakeServiceImpl earthQuakeService;

    public EarthQuakeApplicationController(EarthQuakeServiceImpl earthQuakeService) {
        this.earthQuakeService = earthQuakeService;
    }

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
}
