package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PGSQLConnect {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/db_test2";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Dfhbfyn!23";

    private Connection connection;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("username", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
