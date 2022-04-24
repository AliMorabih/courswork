package com.napier.courswork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    // Display the 4 populated Cities in a Continent
    public ArrayList<City> getFourPopulatedCityByContinent(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will select City by Continent
            String strSelect =   "SELECT city.ID, city.Name, city.CountryCode, city.Population, country.Continent "
                    +" FROM city, country"
                    +" WHERE city.CountryCode = country.Code "
                    +" AND Continent IN ('South America') "
                    +" AND  (city.Population > '4703954')";

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
    // Display the 4 populated Cities in a region
    public ArrayList<City> getFourPopulatedCityByRegion(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will select City by Continent
            String strSelect =   " SELECT city.ID, city.Name, city.CountryCode, city.Population, country.Continent, country.Region "
                +"FROM city, country  "
                +"WHERE city.CountryCode = country.Code  "
                +"AND country.Region IN ('Eastern Europe')  "
                +"AND  (city.Population > '1811552')  "
                +"ORDER BY Population DESC ;  " ;

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
            System.out.println("Failed to get Cities  By Region of  Eastern Europe");
            return null;
        }
    }
    // Display the top four  populated cities in the world
    public ArrayList<City> getFourPopulatedCityWorld(Connection con) {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will select City by Continent
            String strSelect = "SELECT city.ID, city.Name, city.CountryCode, city.Population, country.Continent, country.Region "
                +"FROM city, country "
                +"WHERE city.CountryCode = country.Code"
                +" AND  (city.Population > '9604900')"
                +"ORDER BY Population DESC ";

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
            System.out.println("Failed to get Cities  in the World ");
            return null;
        }
}

    /**
     * Outputs to Markdown
     *
     * @param cities
     */

    public void printCities(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| ID |  Name | Population | Country Code |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.ID + " | " +  con.Name + " | " + con.Population + " | " +   con.CountryCode + " | \r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new                        File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}