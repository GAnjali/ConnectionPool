import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class ConnectionPoolLimitExceeded extends Exception {
    ConnectionPoolLimitExceeded() {
        super("Connection pool limit exceeded, cannot give connections");
    }
}

public class BasicConnectionPool implements ConnectionPool {

    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<Connection>();
    private static int POOL_SIZE = 5;

    public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }


    public static BasicConnectionPool create(
            String url, String user,
            String password) throws SQLException {

        List<Connection> pool = new ArrayList<Connection>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(createDBConnection(url, user, password));
        }
        return new BasicConnectionPool(url, user, password, pool);
    }

    @Override
    public Connection getConnection() throws SQLException, ConnectionPoolLimitExceeded {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < POOL_SIZE) {
                connectionPool.add(createDBConnection(url, user, password));
            } else {
                throw new ConnectionPoolLimitExceeded();
            }
        }
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createDBConnection(String url, String user, String password) throws SQLException {

        DBConnection dbConnection = new DBConnection(url, user, password);
        return dbConnection.createConnection();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}