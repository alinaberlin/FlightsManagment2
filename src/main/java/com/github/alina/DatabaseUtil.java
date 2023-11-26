package com.github.alina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseUtil {
    private static final String url = "jdbc:mysql://localhost:3306/ticket_reservation";
    private static final  String user = System.getenv("USERNAME");
    private static final String password =System.getenv("PASSWORD");
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
