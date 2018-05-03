package com.example.jms.city.service;

import com.example.jms.city.model.City;

public interface CityService {
    City findCityByName(String cityName);
    void addCity(String cityName);
}
