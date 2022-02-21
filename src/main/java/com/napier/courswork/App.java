package com.napier.courswork;

import  java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        CountryExt DAL = new CountryExt();
        // Connect to database
        a.connect();

        //Q1 Display results Countries
        System.out.println("***********************************");
        System.out.println(" Display Countries in the World ");
        ArrayList<Country> country = DAL.getCountry(a.con);
        DAL.printCountry(country);
        //DAL.printCountry(country);

        //Q2 Display  Countries by Continent
        System.out.println("***********************************");
        System.out.println(" Display Countries by Continent Asia ");
        ArrayList<Country> countryC = DAL.getCountryByContinent(a.con);
        DAL.printCountry(countryC);

        // Q3 Display Countries by Region
        System.out.println("*******************************************");
        System.out.println("Display Countries by Region of Eastern Asia ");
        ArrayList<Country> countryR = DAL.getCountryByRegion(a.con);
        DAL.printCountry(countryR);

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
