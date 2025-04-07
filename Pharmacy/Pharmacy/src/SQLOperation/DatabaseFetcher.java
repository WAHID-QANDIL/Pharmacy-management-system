package SQLOperation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseFetcher {
    public static ResultSet select(String query) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement statement;
        ResultSet resultSet ;
        try {
            statement = connection.prepareStatement(query);
       }catch (SQLException e) {
           throw new RuntimeException(e.getMessage(),new Throwable(e.getCause()));
       }
        resultSet = statement.executeQuery(query);
        return resultSet;
    }
}
