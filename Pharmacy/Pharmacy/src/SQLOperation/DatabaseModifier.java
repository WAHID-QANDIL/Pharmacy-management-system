package SQLOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseModifier{
    static Connection connection;

    static {
        try {
            connection = DatabaseConnector.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void modify(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }
}
