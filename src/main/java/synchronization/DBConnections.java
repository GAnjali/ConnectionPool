package synchronization;

import java.sql.Connection;
import java.util.ArrayList;

public class DBConnections {
    private ArrayList<Connection> connectionsList= new ArrayList<Connection>();

    public synchronized void produce(Connection connection) throws InterruptedException {
        while (connectionsList.size()==24){
            wait();
        }
        System.out.println("Produced");
        connectionsList.add(connection);
        notifyAll();
    }

    public synchronized Connection consume() throws InterruptedException {
        while (connectionsList.size()==0){
            wait();
        }
        System.out.println("Consumed");
        Connection connection= connectionsList.get(0);
        connectionsList.remove(connection);
        notifyAll();
        return connection;
    }
}
