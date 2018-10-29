import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String url;
    private String user;
    private String password;

    public DBConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static Connection createConnection(String url, String user, String password)throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
