package com.napier.courswork;

import  java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        // Get Employee
        //Employee emp = a.getEmployee();
        //Employee emp = a.getEmployee(255530 );
        // Display results
        ArrayList<Country> country = a.getEmployee();

        a.printEpmplyeeByRole(country);

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
    /******************************************************************************************************************
     *  Create a SQL statement - a Statement object from the database connection.
     Define the SQL query string to execute.
     Execute a query (executeQuery) to extract data from the database. This will return a ResultSet object.
     Test that the ResultSet has a value - call next on the ResultSet and check this is true.
     Extract the information from the current record in the ResultSet using getInt for integer data, getString for string data, etc.
     *******************************************************************************************************************/
    /**
     * Salaries by Role Feature
     * @return A list of all employees and salaries by Role and we filter by Engineer
     */
    public ArrayList<Country> getEmployee()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
                 String strSelect =
                "SELECT code, name, continent "
                        + "FROM country ";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country emp = new Country();
                emp.Code = rset.getString("country.Code");
                emp.Name = rset.getString("country.name");
                emp.Continent = rset.getString("country.continent");
                country.add(emp);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }


    public void printEpmplyeeByRole(ArrayList<Country> country)
    {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s ", "Emp No", "First Name", "Last Name"));
        // Loop over all employees in the list
        for (Country emp : country)
        {
            String emp_string =
                    String.format("%-10s %-15s %-20s ",
                            emp.Code, emp.Name, emp.Continent);
            System.out.println(emp_string);
        }
    }


}