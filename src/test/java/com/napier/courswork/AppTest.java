package com.napier.courswork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static CityWorld app;

    @BeforeAll
    static void init(){
        app = new CityWorld();
    }
    @Test
    void printCitiesTestNull(){
        app.printCities(null);
    }
    @Test
    void printCitiesTestEmpty(){
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }
    @Test
    void printCitiesTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCities(cities);
    }

}