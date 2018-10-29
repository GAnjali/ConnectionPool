import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

    @Test
    public void connectionTest(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/books_library","postgres","postgres");
            System.out.println("Connection succeeded");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
