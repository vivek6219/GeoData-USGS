package com.snow.geodata;

import com.snow.geodata.entity.EarthQuake;
import com.snow.geodata.service.EarthQuakeService;
import com.snow.geodata.service.EarthQuakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EarthQuakeApplicationController {

    EarthQuakeServiceImpl earthQuakeService;

    public EarthQuakeApplicationController(EarthQuakeServiceImpl earthQuakeService) {
        this.earthQuakeService = earthQuakeService;
    }

    @GetMapping("earthquakes")
  public EarthQuake getEarthQuakeData(){
      return earthQuakeService.earthQuakeList();
  }


}
