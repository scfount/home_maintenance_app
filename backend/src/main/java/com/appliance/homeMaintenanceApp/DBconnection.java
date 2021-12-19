package com.appliance.homeMaintenanceApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static String dbhost = "jdbc:mysql://localhost:3306/appliance_db?useLegacyDatetimeCode=false&serverTimezone=PST";
    private static String username = "root";
    private static String password = "";
    private static Connection connectString;

    @SuppressWarnings("finally")
    public static Connection createNewDBconnection() {
        try  {
            connectString = DriverManager.getConnection(
                    dbhost, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return connectString;
        }
    }
}
