package de.davitec.multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MTTest {

    private static final Logger log = LoggerFactory.getLogger(MultithreadingApplication.class);

    private final static int NUM_CORES = Runtime.getRuntime().availableProcessors();
    private final static long TOTAL_COUNT = 1000000000;

    MTTest() throws InterruptedException {
        log.info("hello world");

        //warmup
        runWith(1);

        //test
        runWith(1);
        runWith(2);
        runWith(4);
        runWith(6);
        runWith(8);
        runWith(NUM_CORES + 1);
        runWith(100);
        runWith(1000);
        //runWith(10000);
    }

    private void runWith(int poolSize) throws InterruptedException {

        if (poolSize < 1) {
            log.warn("must have at least one thread");
            return;
        }

        long average = 0;
        long start = System.nanoTime();
        for (int run = 0; run < 10; run++) { //run 10 times and take the average
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            List<MyThread> threads = new ArrayList<>();
            for (int i = 1; i <= poolSize; i++) {
                MyThread thread = new MyThread(TOTAL_COUNT / poolSize);
                threads.add(thread);
            }

            for (MyThread thread : threads){
                executor.submit(thread);
            }

            executor.shutdown();
            executor.awaitTermination(100, TimeUnit.SECONDS);
        }
        System.gc();
        long end = System.nanoTime();
        log.info(String.format("Pool size %d : %d ms", poolSize, (end-start) / 10 / 1000000 ));
    }
}
