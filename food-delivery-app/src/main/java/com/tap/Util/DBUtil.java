package com.tap.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Utility Class for Connection Management
 * Handles MySQL connection pooling and error management
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASS = "Pramod@2003"; // Change this to your MySQL password

    /**
     * Establishes and returns a database connection
     * @return Connection object
     * @throws ClassNotFoundException if MySQL driver not found
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
