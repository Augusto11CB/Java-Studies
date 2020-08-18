package aug.bueno.blocking.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerMain {

    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

        Producer p1 = new Producer(1, queue);
        Producer p2 = new Producer(2, queue);
        Producer p3 = new Producer(3, queue);

        Consumer c1 = new Consumer(1, queue);
        Consumer c2 = new Consumer(2, queue);

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        System.out.println("availableProcessors: " + availableProcessors);

        ExecutorService service = Executors.newFixedThreadPool(availableProcessors);

        service.execute(p1);
        service.execute(p2);
        service.execute(p3);

        service.execute(c1);
        service.execute(c2);

        service.shutdown();
    }
}
