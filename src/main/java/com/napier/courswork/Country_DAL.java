package com.napier.courswork;

import java.sql.*;

public class Country_DAL {

    public Country getCountry(Connection con, String Code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect =
                    "SELECT code, name, continent,region,population "
                            + "FROM country "
                            + "WHERE code = '" + Code + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            if (rset.next())
            {

                Country ctry = new Country();
                ctry.Code = rset.getString("code");
                ctry.Name = rset.getString("name");
                ctry.Region = rset.getString("region");
                ctry.Population = rset.getInt("population");
                ctry.Continent = rset.getString("continent");
                return ctry;
            }
            else {
                System.out.println("No records");
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountry(Country ctry)
    {
        if (ctry != null)
        {
            System.out.println(
                    "Country Code: " +  ctry.Code + "\n"
                            + "Country Name: " + ctry.Name + "\n"
                            + "Continent: " + ctry.Continent + "\n"
                            + "Region: " + ctry.Region + "\n"
                            + "Population: " + ctry.Population + "\n");
        }
    }
}
