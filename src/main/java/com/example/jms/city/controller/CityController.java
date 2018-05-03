package com.example.jms.city.controller;

import com.example.jms.city.model.City;
import com.example.jms.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @RequestMapping(value = "/city/{cityName}", method = RequestMethod.GET)
    public City city(@PathVariable String cityName) {
        return service.findCityByName(cityName);
    }

    @RequestMapping(value = "/city/add/{cityName}", method = RequestMethod.GET)
    public void addCity(@PathVariable String cityName) {
        service.addCity(cityName);
    }
}
