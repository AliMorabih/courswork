package com.napier.courswork;

import  java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Create Instances
        CountryExt DAL = new CountryExt();
        CityWorld CIT = new CityWorld();

        // Connect to our database Mysql
        a.connect();


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
        ArrayList<Country> countryC = DAL.getCountryByContinent(a.con);
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
    public void connect(){

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
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
