package com.napier.courswork;

import java.util.ArrayList;
import java.sql.*;

public class CountryExt {

    public ArrayList<Country> getCountry(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create SQL Query
            String strSelect =
                    "SELECT code, name, continent, region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC " ;


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Countries information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cont = new Country();
                cont.Code = rset.getString("country.Code");
                cont.Name = rset.getString("country.name");
                cont.Continent = rset.getString("country.continent");
                cont.Region = rset.getString("country.Region");
                cont.Population = rset.getInt("country.population");
                cont.Capital = rset.getInt("country.Capital");
                country.add(cont);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }
    public void printCountry(ArrayList<Country> country)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-15s %-20s %-20s %-20s ", "Code", "Name", "Continent" , "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country cont : country)
        {
            String emp_string =
                    String.format("%-10s %-30s %-15s %-20s %-20s %-20s",
                            cont.Code, cont.Name, cont.Continent,  cont.Region, cont.Population, cont.Capital);
            System.out.println(emp_string);
        }
    }
}