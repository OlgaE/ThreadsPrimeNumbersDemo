package producer.consumer;

import java.util.Vector;

public class ProducerConsumerSolution {

    public static void main(String args[]) {
        Vector<Integer> sharedQueue = new Vector<Integer>();
        int size = 4;
        
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedQueue), "Consumer");
        prodThread.start();
        consThread.start();
    }
}