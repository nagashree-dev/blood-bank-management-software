package org.bloodbank.utils;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bloodbank";
    private static final String USER = "root";  // Change if you have a different username
    private static final String PASSWORD = "root";  // Change if you have a password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database Driver not found!", e);
        }
    }
}
