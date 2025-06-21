// src/main/java/com/hostel/util/DBConnection.java
package com.hostel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Establishes and returns a database connection.
     * @return A Connection object to the database.
     * @throws SQLException If a database access error occurs or the URL is null.
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your project classpath.");
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            throw e; // Re-throw the exception for the caller to handle
        }
        return connection;
    }

    /**
     * Closes the given database connection.
     * @param connection The Connection object to close.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the database connection.");
                e.printStackTrace();
            }
        }
    }
}
