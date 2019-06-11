package synchronization;

import org.junit.jupiter.api.Test;

public class ProducerConsumerTest {

    @Test
    public void test() throws InterruptedException {
        DBConnections dbConnections= new DBConnections();
        Producer producer= new Producer(dbConnections);
        Consumer consumer= new Consumer(dbConnections);
        Consumer consumer1=new Consumer(dbConnections);
        Thread thread1= new Thread(producer);
        Thread thread2= new Thread(consumer);
        Thread thread3= new Thread(consumer1);
        System.out.println(thread1.getState());
        thread1.start();
        System.out.println(thread1.getState());
        thread2.start();
        System.out.println(thread1.getState());
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread3.join();
    }

}
