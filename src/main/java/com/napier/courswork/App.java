package com.napier.courswork;

import  java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to our database Mysql
        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        // Create Instances
        CountryExt DAL = new CountryExt();
        CityWorld CIT = new CityWorld();
        PopulationDAL POP = new PopulationDAL();


        // All the countries in the world organised by largest population to smallest.
        System.out.println("*******************************************");
        System.out.println(" Display Countries in the World ");
        System.out.println("*******************************************");
        ArrayList<Country> country = DAL.getCountry(a.con);
        DAL.printCountry(country,"getCountry.md");

        // All the countries in a continent organised by largest population to smallest.
        //********************Done**************************************************
        System.out.println("******************************************");
        System.out.println(" Display Countries by Continent Asia ");
        System.out.println("*******************************************");
        ArrayList<Country> countryC = DAL.getCountryByContinent (a.con);
        DAL.printCountryByContinent(countryC,"getCountryByContinent.md");

        // All the countries in a region organised by largest population to smallest.
        //
        System.out.println("*******************************************");
        System.out.println(" Display Countries by Region of Eastern Asia ");
        System.out.println("*******************************************");
        ArrayList<Country> countryR = CIT.getCountryByRegion(a.con);
        CIT.printCountryByRegion(countryR,"getCountryByRegion.md");

        // All the cities in the world organised by largest population to smallest.
        // ****************Done******************************************
        System.out.println("*******************************************");
        System.out.println(" Display the  cities in the world ");
        System.out.println("*******************************************");
        ArrayList<City> Cities = CIT.getCityByPopulation(a.con);
        CIT.printCities(Cities, "cities.md");


        // All the cities in a continent organised by largest population to smallest.
        //**********************Done********************************************
        System.out.println("*******************************************");
        System.out.println(" Display the  cities by Continent  ");
        System.out.println("*******************************************");
        ArrayList<City> CitiesC = CIT.getCityByContinent(a.con);
        CIT.printCities(CitiesC, "citiesByContinent.md");


        // Population
        System.out.println("*******************************************");
        System.out.println(" TOP 5 populated countries in the world ");
        System.out.println("*******************************************");
        ArrayList<Population> populations = POP.getTopNPopulatedCountries(a.con);
        POP.printPopulation(populations,"getTopNPopulatedCountries.md");

        // Continent
        System.out.println("*******************************************");
        System.out.println("The top 5 populated countries in a continent  ");
        System.out.println("*******************************************");
        ArrayList<Population> continents = POP.getTopNPopulatedCountriesGroupByContinent(a.con);
        POP.printPopulation(continents,"getTopNPopulatedCountriesGroupByContinent.md");


        // Region
        System.out.println("*******************************************");
        System.out.println("The top 5 populated countries in a region ");
        System.out.println("*******************************************");
        ArrayList<Population> regions = POP.getTopNPopulatedCountriesGroupByRegion(a.con);
        POP.printPopulation(regions,"getTopNPopulatedCountriesGroupByRegion.md");


        // Top 4 Populated cities in a Continent of South America
        //******************************Done*************************************
        System.out.println("**********************************************************************");
        System.out.println("**Display the Top 4 populated cities in a continent Of South America**");
        System.out.println("**********************************************************************");
        ArrayList<City> Citi = CIT.getFourPopulatedCityByContinent(a.con);
        CIT.printgetFourPopulatedCityByContinent(Citi, "getFourPopulatedCityByContinent.md");


        // The Top 4 Populated cities in a region of Eastern Europe
        System.out.println("*************************************************************");
        System.out.println("**The top 4 populated cities in a region of Eastern Europe **");
        System.out.println("*************************************************************");
        ArrayList<City> CitiR = CIT.getFourPopulatedCityByRegion(a.con);
        CIT.printFourPopulatedCityByRegion(CitiR, "getFourPopulatedCityByRegion.md");



        //  Display the top four  populated cities in the world
        //
        System.out.println("*************************************************************");
        System.out.println("****Display the top four  populated cities in the world******");
        System.out.println("*************************************************************");
        ArrayList<City> CitiW = CIT.getFourPopulatedCityWorld(a.con);
        CIT.printPopulatedCityWorld(CitiW, "getFourPopulatedCityWorld.md");



        // Region Cities By Population
        //*****************************Done**************************************************
        System.out.println("*************************************************************");
        System.out.println("****Region Cities By Population******");
        System.out.println("*************************************************************");
        //ArrayList<City> RegionCitiesByPopulation = CIT.getRegionCitiesByPopulation(a.con);
        //CIT.printRegionCitiesByPopul(RegionCitiesByPopulation, "RegioncitiesByContinent.md");

        // Capital Cities By Populations Desc
        //***********Done*****************************************************************
        System.out.println("*************************************************************");
        System.out.println("****Region Cities By Population DESC******");
        System.out.println("*************************************************************");
        //ArrayList<City> CapitalCitiesByPopDesc = CIT.getCapitalCitiesByPopDesc(a.con);
        //CIT.printCities(CapitalCitiesByPopDesc, "CapitalCitiesByPopDesc.md");

        // People Cities Countries
        System.out.println("*************************************************************");
        System.out.println("**** People Cities Countries******");
        System.out.println("*************************************************************");
        ArrayList<City> PeopleCitiesC = CIT.getPeopleCitiesCountries(a.con);
        CIT.printCities(PeopleCitiesC, "PeopleCitiesC.md");


        //
        System.out.println("*************************************************************");
        System.out.println("**********");
        System.out.println("*************************************************************");
        ArrayList<City> PO = CIT.getPeopleCitiesCountries(a.con);
        CIT.printRegionCitiesByPopulation(PO, "PeopleCitiesCount.md");


        //The top N populated capital cities in a region where N is provided by the user.
        System.out.println("*************************************************************");
        System.out.println("*****The top N populated capital cities in a region*****");
        System.out.println("*************************************************************");
        ArrayList<City> CapitalCityRegion = CIT.getTopNCapitalCityRegion(a.con);
        CIT.printTopNCapitalCityRegion(CapitalCityRegion, "getTopNCapitalCityRegion.md");

        //Done
         System.out.println("*************************************************************");
        System.out.println("*****All Capital City Continent *****");
        System.out.println("*************************************************************");
        ArrayList<City> CapitalCityContinent = CIT.getAllCapitalCityContinent(a.con);
        CIT.printAllCapitalCityContinent(CapitalCityContinent, "getAllCapitalCityContinent.md");

        //
        System.out.println("*************************************************************");
        System.out.println("***** Capital City Region *****");
        System.out.println("*************************************************************");
        ArrayList<City> CapitalCityRe = CIT.getAllCapitalCityRegion(a.con);
        CIT.printAllCapitalCityRegion(CapitalCityRe, "getAllCapitalCityRegion.md");




        //Disconnect from database
        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */

    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database This will allow us to run  it locally
                // con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public City getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                 "SELECT city.ID, city.Name, city.CountryCode, city.Population "
                    +"FROM city, country "
                    +"WHERE city.CountryCode = country.Code"
                    +" AND  ID = " + ID;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City ct = new City();
                ct.ID = rset.getInt("city.ID");
                ct.Name = rset.getString("city.Name");
                ct.Population = rset.getLong("city.Population");
                return ct;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }
}
