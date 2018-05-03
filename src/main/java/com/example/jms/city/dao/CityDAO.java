package com.example.jms.city.dao;

import com.example.jms.city.model.City;

public interface CityDAO {
    City findCity(String cityName);
    void addCity(City city);
}
