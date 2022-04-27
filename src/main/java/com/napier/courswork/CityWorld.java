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
                    "SELECT ID, city.name AS cityname, city.population AS citypopulation, country.name AS countryname " +
                               "FROM city, country "
                             + " WHERE city.countrycode = country.code "
                             +    "ORDER BY city.population DESC ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.ID = rset.getInt("city.id");
                cin.Name = rset.getString("cityname");
                cin.CityPopulation = rset.getLong("citypopulation");
                cin.CountryName = rset.getString("countryname");
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
            String strSelect = "SELECT  city.ID, city.Name, city.CountryCode, city.Population, country.Continent, country.name AS  countryname"
                + " FROM city, country "
                + " WHERE city.CountryCode = country.Code "
                + " AND country.Continent = 'Africa'  "
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
                cin.CityPopulation = rset.getLong("city.population");
                cin.CountryCode = rset.getString("city.countryCode");
                cin.CountryName = rset.getString("countryname");
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
            String strSelect =
                      " select * from (select cc.continent,c.name,c.population,cc.name as countryname, "
                    + " row_number() over (partition by cc.continent order by c.population desc) as country_rank "
                            + " from city c inner join country cc on c.id = cc.Capital) "
                            + " ranks  where country_rank <= 4 ";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.Continent = rset.getString("continent");
                cin.Name = rset.getString("name");
                cin.Population = rset.getLong("population");
                cin.CountryName = rset.getString("countryName");
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
            String strSelect =
            " select * from (select cc.region,c.name,c.population,cc.name as countryname, "
              + " row_number() over (partition by cc.region order by c.population desc) as country_rank "
              + " from city c inner join country cc on c.id = cc.Capital) "
              + " ranks  where country_rank <= 4 ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.Region = rset.getString("region");
                cin.Name = rset.getString("name");
                cin.Population = rset.getLong("population");
                cin.CountryName =  rset.getString("countryname");
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
            String strSelect =
                    " SELECT city.Name as cityname, country.Name as countryname, city.Population as citypopulation, country.Continent "
            + " FROM city, country "
            + " WHERE city.CountryCode = country.Code "
            + " ORDER BY city.Population DESC limit 4 ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cin = new City();
                cin.Name = rset.getString("cityname");
                cin.CountryName = rset.getString("countryname");
                cin.CityPopulation = rset.getLong("citypopulation");
                cin.Continent = rset.getString("country.Continent");
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


    public ArrayList<City> getPopulationByRegion(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("All the cities in a region organised by largest population to smallest. \n");
            String strSelect =
                    "SELECT city.id, city.name, country.region, city.Population, country.Name AS countryname, country.Code FROM city "
                            + " INNER JOIN country ON country.Capital=city.ID"
                            + " where country.region = 'Western Europe'"
                            + " order by city.population desc ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.ID = rset.getInt("city.id");
                city.Name = rset.getString("city.name");
                city.Region = rset.getString("country.region");
                city.Population = rset.getLong("city.Population");
                city.CountryName = rset.getString("countryname");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }//End

    public ArrayList<City> getRegionCitiesByPopulation(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The population of people, people living in cities, and people not living in cities in each region. \n");
            String strSelect =
                    "select cc.region, sum(cc.population) regionpopulation,sum(cc.population)-sum(c.citypopulation) as ruralpopulation,"
                            + " sum(c.citypopulation) as citypopulation from country cc inner join"
                            + " (select CountryCode,sum(population) as citypopulation from city"
                            + " group by countrycode) c on c.countrycode = cc.Code"
                            + " group by region";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.Region = rset.getString("region");
                city.Population = rset.getLong("regionpopulation");
                city.CityPopulation = rset.getLong("citypopulation");
                city.RuralPopulation = rset.getLong("ruralpopulation");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    /*
     **********************************Next 3*****************************************************************
     */

    public ArrayList<City> getCapitalCitiesByPopDesc(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("All the capital cities in the world organised by largest population to smallest. \n");

            String strSelect =
                    "SELECT city.id, city.name, country.name, city.Population FROM city "
                            + " INNER JOIN country ON country.Capital=city.ID"
                            + " order by city.population desc ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.ID = rset.getInt("city.id");
                city.Name = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.CityPopulation = rset.getLong("city.Population");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }//End

    /*
     ***********************************End getRegionCitiesByPopulation***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    public ArrayList<City> getPeopleCitiesCountries(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The population of people, people living in cities, and people not living in cities in each country. \n");
            String strSelect =
                    "select cc.name, sum(cc.population) regionpopulation,"
                            + " sum(cc.population)-sum(c.citypopulation) as ruralpopulation,"
                            + " sum(c.citypopulation) as citypopulation from country cc inner join"
                            + " (select CountryCode,sum(population) as citypopulation from city"
                            + " group by countrycode) c on c.countrycode = cc.Code"
                            + " group by cc.name";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.ID = rset.getInt("city.id");
                city.Name = rset.getString("city.name");
                city.CountryName = rset.getString("name");
                city.Population = rset.getLong("regionpopulation");
                city.CityPopulation = rset.getLong("citypopulation");
                city.RuralPopulation = rset.getLong("ruralpopulation");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }


    public ArrayList<Country> getCountryByRegion(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // SQL Query to display All the countries in a region of Eastern Asia organised by largest population to smallest
            String strSelect =
                    " SELECT code, country.Name, continent, region, city.Population "
                            + " FROM country, city "
                            + "WHERE region = 'Eastern Asia' "
                            + "AND country.capital = city.id "
                            + "ORDER BY Population DESC ";



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
            System.out.println("Failed to get Country By Region details");
            return null;
        }
    }
    public void printCountryByRegion(ArrayList<Country> country, String filename) {
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



    /***********************************************/
    //The top N populated capital cities in a region where N is provided by the user.
    public ArrayList<City> getTopNCapitalCityRegion(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated capital cities in a region where N is provided by the user. \n");
            String strSelect =
                    " select * from (select c.name,c.population,cc.region,cc.name as countryname,"
                            + " row_number() over (partition by cc.region order by c.population desc) as country_rank"
                            + " from city c inner join country cc on c.id = cc.Capital) ranks  where country_rank <= 5";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("name");
                city.CityPopulation = rset.getLong("population");
                city.CountryName = rset.getString("countryname");
                city.Region = rset.getString("Region");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public ArrayList<City> getAllCapitalCityContinent(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated cities in the world where N is provided by the user. \n");
            String strSelect =
                    "select * from (select c.name,c.population,cc.continent,cc.name as countryname,"
                            +" row_number() over (partition by cc.continent order by c.population desc) as country_rank"
                            +" from city c inner join country cc on c.id = cc.Capital) ranks";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("name");
                city.CityPopulation = rset.getLong("population");
                city.Continent = rset.getString("countryname");
                city.CountryName = rset.getString("Continent");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public ArrayList<City> getAllCapitalCityRegion(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated cities in the world where N is provided by the user. \n");
            String strSelect =
                    "select * from (select c.name,c.population,cc.region,cc.name as countryname,"
                            +" row_number() over (partition by cc.region order by c.population desc) as country_rank"
                            +" from city c inner join country cc on c.id = cc.Capital) ranks";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("name");
                city.CityPopulation = rset.getLong("population");
                city.Region = rset.getString("Region");
                city.CountryName = rset.getString("countryname");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public ArrayList<City> getTopNPopulatedCapitalCityWorld(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated cities in the world where N is provided by the user. \n");
            String strSelect =
                    " select c.name,c.population,cc.name as countryname"
                            + " from city c inner join country cc on c.id = cc.Capital "
                            + " order by c.population desc limit 5";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> citypop = new ArrayList<>();

            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("name");
                city.CityPopulation = rset.getLong("CityPopulation");
                city.CountryName = rset.getString("countryname");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
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
        sb.append("| ID |  Name | Population | Country Name |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.ID + " | " +  con.Name + " | " + con.CityPopulation + " | " +   con.CountryName + " | \r\n");
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

    // Print Region Cities by populations
    public void printRegionCitiesByPopulation(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| ID |  Name | Population | Country Name | CityPopulation | Ruralpopulation | \r\n");
        sb.append("| --- | --- | --- | --- |  --- | --- | \r\n");
        // Loop over
        for (City cont : cities) {
            if (cont == null) continue;
            sb.append("| " + cont.ID + " | " +  cont.Name + " | " + cont.Population + " | " +   cont.CountryName + " |  " + cont.CityPopulation + " | " +   cont.RuralPopulation + " | \r\n");
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

    public void printRegionCitiesByPopul(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Region |  RegionPopulation | CityPopulation |  Ruralpopulation | \r\n");
        sb.append("| --- | --- | --- | ---  | \r\n");
        // Loop over
        for (City cont : cities) {
            if (cont == null) continue;
            sb.append("| " + cont.Region + " | " +  cont.Population + " | " + cont.CityPopulation + " | " +   cont.RuralPopulation + " | \r\n");
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

    public void printgetFourPopulatedCityByContinent(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Continent |  Name | Population | Country Name |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Continent + " | " +  con.Name + " | " + con.Population + " | " +   con.CountryName + " | \r\n");
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

    public void printFourPopulatedCityByRegion(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| region |  Name | Population | Country Name |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Region + " | " +  con.Name + " | " + con.Population + " | " +   con.CountryName + " | \r\n");
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


    public void printPopulatedCityWorld(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name |  CountryName | CityPopulation | Continent |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Name + " | " +  con.CountryName + " | " + con.CityPopulation + " | " +   con.Continent + " | \r\n");
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

    public void printTopNCapitalCityRegion(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | CiyPopulation | CountryName | Region |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Name + " | " +  con.CityPopulation + " | " + con.CountryName + " | " +   con.Region + " | \r\n");
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

    public void printAllCapitalCityContinent(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name |  CityPopulation | Continent | CountryName |\r\n");
        sb.append("| --- | --- | --- | --- |\r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Name + " | " +  con.CityPopulation + " | " + con.Continent + " | " +   con.CountryName + " | \r\n");
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

    public void printAllCapitalCityRegion(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name |  CityPopulation | Region | Country Name |\r\n");
        sb.append("| --- | --- | --- | --- | \r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Name + " | " +  con.CityPopulation + " | " + con.Region + " | " +   con.CountryName + " | \r\n");
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


    public void printTopNPopulatedCapitalCityWorld(ArrayList<City> cities, String filename) {
        // Check employees is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Population | Country Name |\r\n");
        sb.append("| --- | --- | --- |\r\n");
        // Loop over
        for (City con : cities) {
            if (con == null) continue;
            sb.append("| " + con.Name + " | " +  con.CityPopulation + " | " + con.CountryName + " |\r\n");
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
