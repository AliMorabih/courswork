package com.napier.courswork;

import java.util.ArrayList;
import java.sql.*;

public class CityWorld {

    public ArrayList<City> cities = new ArrayList<City>();

    public ArrayList<City> getCityByPopulation(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will select City By Population
            String strSelect =
                    "SELECT ID, NAME, Population " +
                            "FROM city " +
                            "ORDER BY Population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.ID = rset.getInt("city.id");
                cin.Name = rset.getString("city.name");
                cin.Population = rset.getInt("city.population");
                cities.add(cin);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities details");
            return null;
        }
    }
    public ArrayList<City> getCityByContinent(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will select City by Continent
            String strSelect = "SELECT  city.ID, city.Name, city.CountryCode, city.Population, country.Continent"
                    + " FROM city, country "
                    + " WHERE city.CountryCode = country.Code "
                    + " AND country.Continent IN ('Africa')  "
                    + "ORDER BY Population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.ID = rset.getInt("city.id");
                cin.Name = rset.getString("city.name");
                cin.Population = rset.getInt("city.population");
                cin.CountryCode = rset.getString("city.countryCode");
                cities.add(cin);

            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities  By Continent details");
            return null;
        }
    }

    public ArrayList<City> getTopNPopulatedCapitalCitiesInWorld(Connection con, Integer n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            System.out.println("•\tThe top N populated capital cities in the world where N is provided by the user\n");

            String strSelect =
                    "SELECT city.Name , city.Population\n" +
                            "FROM country c\n" +
                            "LEFT JOIN city city on city.ID = c.Capital\n" +
                            "ORDER BY Population DESC LIMIT " + n;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cities = new ArrayList<>();

            while (rset.next())
            {
                City cin = new City();
                cin.Name = rset.getString("city.name");
                cin.Population = rset.getInt("city.population");
                cities.add(cin);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region Population details");
            return null;
        }
    }

    public void printCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-15s  %15s " , "ID", "Name", "Population",  "Country Code"));
        // Loop over all cities in the list
        for (City con : cities)
        {
            String city_string =
                    String.format("%-10s %-30s %-15s %15s ",
                            con.ID, con.Name, con.Population, con.CountryCode);
            System.out.println(city_string);
        }
    }

}