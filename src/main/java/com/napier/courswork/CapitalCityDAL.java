package com.napier.courswork;

import java.util.ArrayList;
import java.sql.*;

public class CapitalCityDAL {

    public ArrayList<CapitalCity> getTopNPopCapCities(Connection con, Integer TopN) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("Top N populated capital cities in the world \n");
            String strSelect =
                    "SELECT city.id, city.name, country.name,country.region, city.Population,country.continent FROM `city` "
                            + " INNER JOIN country ON country.Capital=city.ID order by population desc limit " + TopN;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityCode = rset.getInt("city.id");
                city.CityName = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.Continent = rset.getString("country.continent");
                city.Region = rset.getString("country.region");
                city.Population = rset.getDouble("city.population");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printTopNPopCapCities(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-15s %-20s %-20s ", "Code", "Name", "Continent", "Region", "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-15s %-20s %-20s ",
                            cont.CityCode, cont.CityName, cont.Continent, cont.Region, cont.Population);
            System.out.println(emp_string);
        }
    }

    /*
     *************************End getTopNPopCapCities*************************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */


    public ArrayList<CapitalCity> getPopulationByRegion(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("All the cities in a region organised by largest population to smallest. \n");
            String strSelect =
                    "SELECT city.id, city.name, country.region, city.Population FROM city "
                            + " INNER JOIN country ON country.Capital=city.ID"
                            + " where country.region = 'Western Europe'"
                            + " order by city.population desc ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityCode = rset.getInt("city.id");
                city.CityName = rset.getString("city.name");
                city.Region = rset.getString("country.region");
                city.Population = rset.getDouble("city.Population");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }//End

    public void printPopulationByRegion(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No City region Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-20s %-30s %-20s %-20s ", "ID", "City", "Region", "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;
            String emp_string =
                    String.format("%-20s %-30s %-20s %-20s ",
                            cont.CityCode, cont.CityName, cont.Region, cont.Population);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getPopulationByRegion***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */
    public ArrayList<CapitalCity> getRegionCitiesByPopulation(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.Region = rset.getString("region");
                city.Population = rset.getDouble("regionpopulation");
                city.CityPopulation = rset.getDouble("citypopulation");
                city.RuralPopulation = rset.getDouble("ruralpopulation");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printRegionCitiesByPopulation(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s ", "Region", "Region Population", "City Population", "Rural Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;


            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s ",
                            cont.Region, cont.Population, cont.RuralPopulation, cont.CityPopulation);
            System.out.println(emp_string);
        }
    }
    ///////


    /*
     ***********************************End getRegionCitiesByPopulation***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    /*
     **********************************Next 3*****************************************************************
     */

    public ArrayList<CapitalCity> getCapitalCitiesByPopDesc(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityCode = rset.getInt("city.id");
                city.CityName = rset.getString("city.name");
                city.CountryName = rset.getString("country.name");
                city.Population = rset.getDouble("city.Population");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }//End

    public void printCapitalCitiesByPopDesc(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No City region Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-20s %-30s %-20s %-20s ", "ID", "City", "Name", "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;
            String emp_string =
                    String.format("%-20s %-30s %-20s %-20s ",
                            cont.CityCode, cont.CityName, cont.CountryName, cont.Population);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getRegionCitiesByPopulation***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

c        try {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CountryName = rset.getString("name");
                city.Population = rset.getDouble("regionpopulation");
                city.CityPopulation = rset.getDouble("citypopulation");
                city.RuralPopulation = rset.getDouble("ruralpopulation");

                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printPeopleCitiesCountries(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s ", "Country", "Region Population", "City Population", "Rural Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s ",
                            cont.CountryName, cont.Population, cont.RuralPopulation, cont.CityPopulation);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getRegionCitiesByPopulation***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The top N populated capital cities in a continent where N is provided by the user.

    public ArrayList<CapitalCity> getTopNPopulatedCapitalinContinent(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated capital cities in a continent where N is provided by the user. \n");
            String strSelect =
                    "select * from (select cc.continent,c.name,c.population,cc.name as countryname,"
                            + " row_number() over (partition by cc.continent order by c.population desc) as country_rank"
                            + " from city c inner join country cc on c.id = cc.Capital"
                            + " ) ranks  where country_rank <= 5";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
                city.Continent = rset.getString("continent");
                city.CountryName = rset.getString("countryname");
                city.Rank = rset.getInt("country_rank");


                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printTopNPopulatedCapitalinContinent(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s ", "City", "Country", "Continent", "Population","Rank"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName, cont.Continent, cont.Population,cont.Rank);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getTopNPopulatedCapitalinContinent***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The top N populated capital cities in a continent where N is provided by the user.

    public ArrayList<CapitalCity> getTopNPopulatedCityCountry(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated cities in a country where N is provided by the user. \n");
            String strSelect =
                    "select * from (select c.name,c.population,cc.name as countryname,cc.continent,"
                            + " row_number() over (partition by cc.name order by c.population desc) as country_rank"
                            + " from city c inner join country cc on c.CountryCode = cc.Code"
                            + " ) ranks  where country_rank <= 5";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
                city.Continent = rset.getString("continent");
                city.CountryName = rset.getString("countryname");
                city.Rank = rset.getInt("country_rank");


                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printTopNPopulatedCityCountry(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s ", "City", "Country", "Continent", "Population","Rank"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName, cont.Continent, cont.Population,cont.Rank);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getTopNPopulatedCityCountry***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The top N populated capital cities in a continent where N is provided by the user.

    public ArrayList<CapitalCity> getTopNPopulatedCityDistrict(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated cities in a country where N is provided by the user. \n");
            String strSelect =
                    " select * from (select c.name,c.population,c.District,cc.name as countryname,"
                            + " row_number() over (partition by c.District order by c.population desc) as country_rank"
                            + " from city c inner join country cc on c.CountryCode = cc.Code"
                            + " ) ranks  where country_rank <= 5 and District <> ''";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
                city.District = rset.getString("District");
                city.CountryName = rset.getString("countryname");
                city.Rank = rset.getInt("country_rank");


                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }

    public void printTopNPopulatedCityDistrict(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s ", "City", "Country", "District", "Population","Rank"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName, cont.District, cont.Population,cont.Rank);
            System.out.println(emp_string);
        }
    }

    /*
     ***********************************End getTopNPopulatedCityDistrict***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The top N populated capital cities in the world where N is provided by the user.

    public ArrayList<CapitalCity> getTopNPopulatedCapitalCityWorld(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
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

    public void printTopNPopulatedCapitalCityWorld(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s ", "City", "Country",  "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName, cont.Population);
            System.out.println(emp_string);
        }
    }
    /*
     ***********************************End getTopNPopulatedCapitalCityWorld***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //All the capital cities in a region organised by largest to smallest.
    public ArrayList<CapitalCity> getAllCapitalCityRegion(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
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


    public void printAllCapitalCityRegion(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s ", "City", "Country",  "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName, cont.Population);
            System.out.println(emp_string);
        }
    }
    /*
     ***********************************End getAllCapitalCityRegion***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //All the capital cities in a continent organised by largest population to smallest.
    public ArrayList<CapitalCity> getAllCapitalCityContinent(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
                city.CountryName = rset.getString("countryname");
                city.Continent = rset.getString("Continent");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }


    public void printAllCapitalCityContinent(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s ", "City", "Country","Continent",  "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName,cont.Continent, cont.Population);
            System.out.println(emp_string);
        }
    }
    /*
     ***********************************End getAllCapitalCityContinent***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The top N populated capital cities in a region where N is provided by the user.
    public ArrayList<CapitalCity> getTopNCapitalCityRegion(Connection con) {
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

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.CityName = rset.getString("name");
                city.Population = rset.getDouble("population");
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


    public void printTopNCapitalCityRegion(ArrayList<CapitalCity> CapitalCity) {
        if (CapitalCity == null) {
            System.out.println("No Capital City Data");
            return;
        }
        // Print header

        System.out.println(String.format("%-30s %-30s %-30s %-30s ", "City", "Country","Region",  "Population"));
        // Loop over all employees in the list
        for (CapitalCity cont : CapitalCity) {
            if (cont == null)
                continue;

            String emp_string =
                    String.format("%-30s %-30s %-30s %-30s ",
                            cont.CityName, cont.CountryName,cont.Region, cont.Population);
            System.out.println(emp_string);
        }
    }
    /*
     ***********************************End getTopNCapitalCityRegion***************************************************************
     *************************************************************************************************
     **************************************************************************************************
     *************************************************************************************************
     */

    //The population of people, people living in cities, and people not living in cities in each continent.
   /* public ArrayList<CapitalCity> getPopCityContinent(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("The top N populated capital cities in a region where N is provided by the user. \n");
            String strSelect =
                    "select cc.Continent, sum(cc.population) regionpopulation,sum(cc.population)-sum(c.citypopulation) as ruralpopulation,"
                            +" sum(c.citypopulation) as citypopulation from country cc inner join"
                            +" (select CountryCode,sum(population) as citypopulation from city"
                            +" group by countrycode) c on c.countrycode = cc.Code"
                            +" group by Continent";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCity> citypop = new ArrayList<>();

            while (rset.next()) {
                CapitalCity city = new CapitalCity();
                city.Continent = rset.getString("Continent");
                city.Population = rset.getDouble("regionpopulation");
                city.CityPopulation = rset.getDouble("citypopulation");
                city.RuralPopulation = rset.getDouble("ruralpopulation");
                citypop.add(city);
            }
            return citypop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Capital details");
            return null;
        }
    }*/



}//end class
