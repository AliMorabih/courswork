package com.napier.courswork;

import java.util.ArrayList;
import java.sql.*;

public class PopulationDAL {
    public ArrayList<Population> getTopNPopulatedCountries(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("•The top 5 populated countries in the world . \n");

            String strSelect =
                    "select   continent, region, name,population "
                            + "from country order by population desc limit 5 ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Population> pop = new ArrayList<>();

            while (rset.next())
            {
                Population population = new Population();
                population.country = rset.getString("country.name");
                population.continent = rset.getString("country.continent");
                population.region = rset.getString("country.region");
                population.Population = rset.getInt("country.population");
                pop.add(population);
            }
            return pop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world Population details");
            return null;
        }
    }//End getWorldCityListByPop

    public ArrayList<Population> getTopNPopulatedCountriesGroupByContinent(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            System.out.println("•\tThe top 5 populated countries in a continent  \n");

            String strSelect =
                    "SELECT c.continent, c.region, c.name,c.population\n" +
                            "FROM country c\n" +
                            "WHERE 5 > (\n" +
                            "           SELECT COUNT(DISTINCT population)\n" +
                            "           FROM country c2\n" +
                            "           WHERE c2.population > c.population\n" +
                            "           AND c.continent = c2.continent\n" +
                            "          ) order by c.continent ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Population> pop = new ArrayList<>();

            while (rset.next())
            {
                Population continent = new Population();
                continent.country = rset.getString("c.name");
                continent.region = rset.getString("c.region");
                continent.continent = rset.getString("c.continent");
                continent.Population = rset.getInt("c.Population");
                pop.add(continent);
            }
            return pop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Continent Population details");
            return null;
        }
    }//End getWorldContinentListByPop

    public ArrayList<Population> getTopNPopulatedCountriesGroupByRegion(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            System.out.println("•\tThe top 5 populated countries in a region  \n");

            String strSelect =
                    "SELECT c.continent, c.region, c.name,c.population\n" +
                            "FROM country c\n" +
                            "WHERE 5 > (\n" +
                            "           SELECT COUNT(DISTINCT population)\n" +
                            "           FROM country c2\n" +
                            "           WHERE c2.population > c.population\n" +
                            "             AND c.region = c2.region\n" +
                            "          ) order by c.region";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Population> pop = new ArrayList<>();

            while (rset.next())
            {
                Population continent = new Population();
                continent.region = rset.getString("c.region");
                continent.country = rset.getString("c.name");
                continent.continent = rset.getString("c.continent");
                continent.Population = rset.getInt("c.Population");
                pop.add(continent);
            }
            return pop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region Population details");
            return null;
        }
    }//End getWorldContinentListByPop

    public void printPopulation(ArrayList<Population> population)
    {
        // Print header
        System.out.println(String.format("%-30s %-30s %-20s %-20s ", "Continent", "Country Name", "Region" , "Population"));
        // Loop over all employees in the list
        for (Population pop : population)
        {
            String emp_string =
                    String.format("%-30s %-30s %-20s %-20s",
                            pop.continent, pop.country, pop.region, pop.Population);
            System.out.println(emp_string);
        }
    }
}
