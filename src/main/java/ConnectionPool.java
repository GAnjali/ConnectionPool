import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection() throws SQLException, ConnectionPoolLimitExceeded;
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
