/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooc_db.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CA 1 Integrated Databases, Cloud and OCC (20%)
 * @author Students:
 * Rhuan Mendanha Eli Raimundo & Henrique Queiroz de Morais.
 * Student Number: (Rhuan)2023186 & (Henrique)2023288.
 */

public class DatabaseConnection {
    
    // The URL specifies where the database is located and its name.
    private static final String URL = "jdbc:mysql://localhost:3306/OOC_DB";
    
    // Fixed credentials (username and password) for database access.
    private static final String USERNAME = "ooc2023";
    private static final String PASSWORD = "ooc2023";

    // This method is responsible for establishing a connection to the database.
    public static Connection getConnection() throws SQLException {
        
        try {
            // Load the MySQL JDBC driver. This is required to talk to a MySQL database.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        
        catch (ClassNotFoundException e) {
            // If the driver is not found, we let the user know and throw an exception.
            System.err.println("\u001B[31m Error: MySQL JDBC Driver not found. \u001B[0m");
            throw new SQLException("Driver not found.", e);
        }

        // Attempt to connect to the database using the URL, username, and password.
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}