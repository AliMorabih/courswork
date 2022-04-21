package com.napier.courswork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static CityWorld app;
    static CityPopulationDAL appc;
    static CountryExt appce;
    static PopulationDAL appd;

    @BeforeAll
    static void init(){
        app = new CityWorld();
        appc = new CityPopulationDAL();
        appce = new CountryExt();
        appd = new PopulationDAL();
    }
    @Test
    void printCitiesTestContainsNull(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCities(cities);
    }
    @Test
    void printCityPopulationNull(){
    appc.printCityPopulation(null);
    }
    @Test
    void printCityPopulationContainNull(){

        ArrayList<CityPopulation> cityPopulation = new ArrayList<CityPopulation>();
        cityPopulation.add(null);
        appc.printCityPopulation(cityPopulation);
    }
    @Test
    void printCountryNull(){
        appce.printCountry(null);
    }
    @Test
    void printCountryContainNull(){

        ArrayList<Country> country = new ArrayList<Country>();
        country.add(null);
        appce.printCountry(country);
    }
    @Test
    void printPopulationNull(){
        appd.printPopulation(null);
    }
    @Test
    void printPopulationContainNull(){

        ArrayList<Population> population = new ArrayList<Population>();
        population.add(null);
        appd.printPopulation(population);
    }
}
