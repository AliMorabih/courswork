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
            // This SQL Query will select countries based on population//
            String strSelect =
                "SELECT name, Population " +
                    "FROM city " +
                    "ORDER BY Population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.id");
                city.Name = rset.getString("city.name");
                city.Population = rset.getInt("country.population");
                cities.add(city);
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

    public void printCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-15s ", "ID", "Name", "Population"));
        // Loop over all cities in the list
        for (City city : cities)
        {
            String city_string =
                    String.format("%-10s %-30s %-15s",
                            city.ID, city.Name, city.Population);
            System.out.println(city_string);
        }
    }
}