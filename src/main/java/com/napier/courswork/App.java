package com.napier.courswork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) {
            // Create new Application and connect to database
            App a = new App();

            if(args.length < 1){
                a.connect("localhost:33060", 30000);
            }else{
                a.connect(args[0], Integer.parseInt(args[1]));
            }


        //_____________
        // Create new Application
        // Create Instances
        CountryExt DAL = new CountryExt();
        CityWorld CIT = new CityWorld();
        PopulationDAL POP = new PopulationDAL();


        // Connect to our database Mysql
      //  a.connect();


        // All the countries in the world organised by largest population to smallest.
        System.out.println("*******************************************");
        System.out.println(" Display Countries in the World ");
        System.out.println("*******************************************");
        ArrayList<Country> country = DAL.getCountry(a.con);
        DAL.printCountry(country);

        // All the countries in a continent organised by largest population to smallest.
        System.out.println("******************************************");
        System.out.println(" Display Countries by Continent Asia ");
        System.out.println("*******************************************");
        ArrayList<Country> countryC = DAL.getCountryByContinent (a.con);
        DAL.printCountry(countryC);

        // All the countries in a region organised by largest population to smallest.
        System.out.println("*******************************************");
        System.out.println(" Display Countries by Region of Eastern Asia ");
        System.out.println("*******************************************");
        ArrayList<Country> countryR = DAL.getCountryByRegion(a.con);
        DAL.printCountry(countryR);

        // All the cities in the world organised by largest population to smallest.
        System.out.println("*******************************************");
        System.out.println(" Display the  cities in the world ");
        System.out.println("*******************************************");
        ArrayList<City> Cities = CIT.getCityByPopulation(a.con);
        CIT.printCities(Cities);
         // All the cities in a continent organised by largest population to smallest.
        System.out.println("*******************************************");
        System.out.println(" Display the  cities by Continent  ");
        System.out.println("*******************************************");
        ArrayList<City> CitiesC = CIT.getCityByContinent(a.con);
        CIT.printCities(CitiesC);

        // Population
        System.out.println("*******************************************");
        System.out.println(" TOP 5 populated countries in the world ");
        System.out.println("*******************************************");
        ArrayList<Population> populations = POP.getTopNPopulatedCountries(a.con);
        POP.printPopulation(populations);

        // Continent
        System.out.println("*******************************************");
        System.out.println("The top 5 populated countries in a continent  ");
        System.out.println("*******************************************");
        ArrayList<Population> continents = POP.getTopNPopulatedCountriesGroupByContinent(a.con);
        POP.printPopulation(continents);


        // Region
        System.out.println("*******************************************");
        System.out.println("The top 5 populated countries in a region ");
        System.out.println("*******************************************");
        ArrayList<Population> regions = POP.getTopNPopulatedCountriesGroupByRegion(a.con);
        POP.printPopulation(regions);

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.println("*******************************************");
        System.out.println("The population of people, people living in cities, and people not living in cities in each continent");
        System.out.println("*******************************************");
        ArrayList<Population> populations1 = POP.getPopulationOfPeopleFromEachContinent(a.con);
        POP.printPopulation1(populations1);

        //The top N populated capital cities in a region where N is provided by the user
        System.out.println("*******************************************");
        System.out.println("The top N populated capital cities in a region where N is provided by the user");
        System.out.println("*******************************************");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        //get user input N
        System.out.println("Enter N: ");
        Integer userInput = scanner.nextInt();
        ArrayList<City> capitalCitiesInRegion = CIT.getTopNPopulatedCapitalCitiesInRegion(a.con, userInput);
        CIT.printCities(capitalCitiesInRegion);

        //The top N populated capital cities in the world where N is provided by the user
       System.out.println("*******************************************");
        System.out.println("The top N populated capital cities in the world where N is provided by the user");
        System.out.println("*******************************************");
        //get user input N
        System.out.println("Enter N: ");
        Integer userInput1 = scanner.nextInt();
        ArrayList<City> capitalCitiesWorld = CIT.getTopNPopulatedCapitalCitiesInWorld(a.con, userInput1);
        CIT.printCities(capitalCitiesWorld);



        //Disconnect from database
        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database
     */


   public void connect(String location, int delay){

        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                  Thread.sleep(3);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                //+923426472313 for docker you should have connection string like this  === "jdbc:mysql://db:3306/world?useSSL=false"
                // reset of the things remain same.

                // when dockerizing the appplication keep int mind you have to create network between both containers and also need to create volumn for data base
                // since you are learning i will suggest first learn about what is volumn and how to creae network between two containers
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /*public void connect(String location, int delay) {
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
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
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


}
