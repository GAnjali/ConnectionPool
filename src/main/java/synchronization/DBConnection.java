package synchronization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/books_library", "postgres", "postgres");;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
