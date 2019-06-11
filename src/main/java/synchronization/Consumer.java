package synchronization;

import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class Consumer implements Runnable {
    private DBConnections dbConnections;

    public Consumer(DBConnections dbConnections) {
        this.dbConnections = dbConnections;
    }

    @Override
    public void run() {
        while (true) {
            int count=0;
            Connection connection= null;
            try {
                connection = dbConnections.consume();
                count+=1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (count==2){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count=0;
            }
        }
    }
}
