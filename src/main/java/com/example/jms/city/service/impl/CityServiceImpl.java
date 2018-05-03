package com.example.jms.city.service.impl;

import com.example.jms.city.dao.CityDAO;
import com.example.jms.city.model.City;
import com.example.jms.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

    private final CityDAO dao;

    @Autowired
    public CityServiceImpl(CityDAO dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public City findCityByName(String cityName) {
        return dao.findCity(cityName);
    }

    @Transactional
    @Override
    public void addCity(String cityName) {
        City city = new City();
        city.setCity(cityName);

        dao.addCity(city);
    }
}
