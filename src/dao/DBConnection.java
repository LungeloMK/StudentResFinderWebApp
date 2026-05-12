package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
     // Database URL (Derby in embedded mode)
    private static final String URL = "jdbc:derby://localhost:1527/studentresfinder;create=true";
    private static final String USER = "app";
    private static final String PASSWORD = "app";

    private static Connection conn;

    // Get connection method
    public static DBConnection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database connection failed!");
                e.printStackTrace();
            }
        }
        return conn;
    }

    // Close connection (good practice)
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
