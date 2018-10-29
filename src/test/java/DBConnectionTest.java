import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBConnectionTest {

    @Test
    public void connectionTest() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/books_library", "postgres", "postgres");
            System.out.println("Connection succeeded");
            //assertTrue(connection.isValid(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ShouldCreatePoolConnection() {
        try {
            ConnectionPool connectionPool = BasicConnectionPool.create("jdbc:postgresql://localhost:5432/books_library", "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection established");
    }

    @Test
    public void ShouldChecksPoolConnectionException() throws SQLException, ConnectionPoolLimitExceeded {
        ConnectionPool connectionPool = BasicConnectionPool.create("jdbc:postgresql://localhost:5432/books_library", "postgres", "postgres");
        List<Connection> connections = new ArrayList<Connection>(5);
        for (int connection_counter = 0; connection_counter < 5; connection_counter++) {
            connections.add(connectionPool.getConnection());
        }
        //assertTrue(connectionPool.releaseConnection(connections.get(5)));
        assertEquals(5, connections.size());
    }
}
