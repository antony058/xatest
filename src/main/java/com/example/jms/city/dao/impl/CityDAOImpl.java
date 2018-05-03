package com.example.jms.city.dao.impl;

import com.example.jms.city.dao.CityDAO;
import com.example.jms.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class CityDAOImpl implements CityDAO {

    private final EntityManager em;

    @Autowired
    public CityDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public City findCity(String cityName) {
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE c.city='" + cityName + "'", City.class);

        return query.getSingleResult();
    }

    @Override
    public void addCity(City city) {
        em.persist(city);
    }
}
