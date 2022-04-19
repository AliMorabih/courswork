package com.napier.courswork;

import java.util.ArrayList;
import java.sql.*;

public class CityPopulationDAL {

    public ArrayList<CityPopulation> getWorldCityListByPop(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("All the cities in the world organised by largest population to smallest \n");

            String strSelect =
                    "SELECT id, city.name, country.name,city.district,city.Population "
                            + "FROM country, city "
                            + " WHERE city.countrycode = country.code "
                            + "ORDER BY city.Population DESC" ;


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityPopulation> citypop = new ArrayList<>();

            while (rset.next())
            {
                CityPopulation city = new CityPopulation();
                city.CityName = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.District = rset.getString("city.district");
                city.Population = rset.getInt("city.population");
                citypop.add(city);
            }
            return citypop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Population details");
            return null;
        }
    }//End getWorldCityListByPop

    public ArrayList<CityPopulation> getCityContinentListByPop(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            System.out.println("All the cities in a continent organised by largest population to smallest \n");

            String strSelect =
                    "SELECT id, city.name, country.name,city.district,city.Population "
                            + " FROM country, city "
                            + " WHERE city.countrycode = country.code "
                            + " and country.continent = 'Asia'"
                            + " ORDER BY city.Population DESC" ;


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityPopulation> citypop = new ArrayList<>();

            while (rset.next())
            {
                CityPopulation city = new CityPopulation();
                city.CityName = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.District = rset.getString("city.District");
                city.Population = rset.getInt("city.population");
                citypop.add(city);
            }
            return citypop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Continent/City Population details");
            return null;
        }
    }//End getWorldContinentListByPop

    public void printCityPopulation(ArrayList<CityPopulation> cityPopulation)
    {
        // Print header
        System.out.println(String.format("%-30s %-30s %-20s %-20s ", "CityName", "Country Name", "District" , "Population"));
        // Loop over all employees in the list
        for (CityPopulation city : cityPopulation)
        {
            String emp_string =
                    String.format("%-30s %-30s %-20s %-20s",
                            city.CityName, city.CountryName, city.District, city.Population);
            System.out.println(emp_string);
        }
    }
}