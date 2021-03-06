package com.napier.courswork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
    * Function to display the countries of Asia Continent
    * */

    public ArrayList<Country> getCountryByContinent(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // This SQL Query will filter only countries in the continent of Asia//
            String strSelect =
                    "SELECT code, name, continent, region, Population "
                            + "FROM country "
                            + " WHERE Continent IN ('Asia') "
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
                country.add(cont);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country By Continent details");
            return null;
        }
    }


    /******************************************************************************
    * All the countries in a region organised by largest population to smallest
    * ***************************************************************************/



    public void printCountry(ArrayList<Country> country, String filename) {
        // Check employees is not null
        if (country == null) {
            System.out.println("No Country");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Code |  Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- | \r\n");
        // Loop over
        for (Country cont : country) {
            if (cont == null) continue;
            sb.append("| " + cont.Code + " | " +  cont.Name + " | " + cont.Continent + " | " +   cont.Region + " | "  +   cont.Population + " | " +   cont.Capital + " | \r\n");
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
    public void printCountryByContinent(ArrayList<Country> country, String filename) {
        // Check employees is not null
        if (country == null) {
            System.out.println("No Country");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Code |  Name | Continent | Region | Population  |\r\n");
        sb.append("| --- | --- | --- | --- | --- |  \r\n");
        // Loop over
        for (Country cont : country) {
            if (cont == null) continue;
            sb.append("| " + cont.Code + " | " +  cont.Name + " | " + cont.Continent + " | " +   cont.Region + " | "  +   cont.Population + "  | \r\n");
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