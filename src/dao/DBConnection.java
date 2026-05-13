package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DEFAULT_URL = "jdbc:derby://localhost:1527/StudentResFinderDB;create=true";
    private static final String DEFAULT_USER = "app";
    private static final String DEFAULT_PASSWORD = "app";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ignored) {
            // GlassFish commonly supplies the Derby driver at runtime.
        }
    }

    public Connection getConnection() throws SQLException {
        String url = System.getProperty("studentresfinder.db.url", DEFAULT_URL);
        String user = System.getProperty("studentresfinder.db.user", DEFAULT_USER);
        String password = System.getProperty("studentresfinder.db.password", DEFAULT_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }
}
