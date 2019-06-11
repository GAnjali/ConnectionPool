package synchronization;

import java.sql.Connection;

import static java.lang.Thread.sleep;

public class Producer implements Runnable{
    private DBConnections dbConnections;
    public Producer(DBConnections dbConnections){
        this.dbConnections=dbConnections;
    }
    @Override
    public void run() {
        while (true) {
            Connection connection= DBConnection.getConnection();
            try {
                dbConnections.produce(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}