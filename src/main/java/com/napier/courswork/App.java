package com.napier.courswork;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        ArrayList<Country> country = a.getCountry();
        a.printInfo(country);
        // Disconnect from database
        a.disconnect();
    }


    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {

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
                Thread.sleep(30000);
                // Connect to database
               // con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world", "root", "example");

               con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=true", "root", "example");

                //con = DriverManager.getConnection("jdbc:mysql://localhost:33060/mysql?" + "user=root & password=example");

                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public ArrayList<Country> getCountry() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                     " select country.Name, country.Region, country.Population, country.Code  from country ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cont = new Country();
                cont.name = rset.getString("country.name");
                cont.region = rset.getString("country.region");
                cont.population = rset.getInt("country.population");
                cont.code = rset.getString("country.code");

                country.add(cont);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country ");
            return null;
        }
        // Display Data
    }

    public void printInfo(ArrayList<Country> country) {
        // Printing the headeer
        System.out.println(String.format("%-10s %-15s %-20s %-8s", " name ", "region", "population", "code"));
        // Loop over all employees in the list
        for (Country cont : country)
        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            cont.name, cont.region, cont.population, cont.code);
            System.out.println(emp_string);
        }

    }


}
