package SQLOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public abstract class DatabaseConnector {

    public static Connection connect() throws SQLException, RuntimeException {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found");
        }

        // Establish the connection
        String url = "jdbc:mysql://localhost:3306/Pharmacy";
        String username = "root";
        String password = "Y123!";
        return DriverManager.getConnection(url, username, password);
    }
}