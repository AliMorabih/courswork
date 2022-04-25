package com.napier.courswork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

public class CapitalCityWorldDAL {
    public ArrayList<CapitalCityWorld> getTopNPopCapCities(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("Top N populated capital cities in the world \n");
            String strSelect =
                    "SELECT city.id, city.name as cityname, country.name as countryname,city.Population,city.district FROM `city` "
                            + " INNER JOIN country ON country.Capital=city.ID order by population desc limit 5";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCityWorld> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCityWorld city = new CapitalCityWorld();
                city.CityId = rset.getInt("city.id");
                city.CityName = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.District = rset.getString("country.region");
                city.Population = rset.getLong("city.population");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }//getTopNPopCapCities

    public void PrintCities(ArrayList<CapitalCityWorld> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| ID |  Name | Population | Country Name |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (CapitalCityWorld con : cities) {
            if (con == null) continue;
            sb.append("| " + con.CityName + " | " +  con.CountryName + " | " + con.District + " | " +   con.Population + " | \r\n");
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


}//end class
