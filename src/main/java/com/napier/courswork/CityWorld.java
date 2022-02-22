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
            // This SQL Query will select countries based on population//
            String strSelect =
                    "SELECT ID, NAME, Population "
                            + " FROM city "
                            + " WHERE Continent IN ('Africa')"
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

    public void printCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-15s ", "ID", "Name", "Population"));
        // Loop over all cities in the list
        for (City con : cities)
        {
            String city_string =
                    String.format("%-10s %-30s %-15s",
                            con.ID, con.Name, con.Population);
            System.out.println(city_string);
        }
    }
}