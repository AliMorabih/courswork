package com.napier.courswork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }
    @Test
    void testGetCityName()
    {

         City cit = app.getCity(2331);
         assertEquals(cit.ID, 2331);
         assertEquals(cit.Name, "Seoul");
    }
}