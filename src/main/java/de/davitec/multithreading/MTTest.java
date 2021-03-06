package de.davitec.multithreading;

import org.apache.commons.lang3.RandomStringUtils;
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
    private final static long TOTAL_COUNT = 10000;

    List<String> readable_input = new ArrayList<>();

    MTTest() throws InterruptedException {
        log.info("hello world");

        for (int i = 0; i < 100; i++){
            String generatedString = RandomStringUtils.random(30, true, true);
            readable_input.add(generatedString);
        }
        log.info(String.format("generated input as array of %d string like %s", readable_input.size(), readable_input.get(0)));
        //warmup
        runWith(1);

        //test
        runWith(1);
        runWith(2);
        runWith(4);
        runWith(6);
        runWith(8);
        runWith(NUM_CORES + 1);
        runWith(20);
        runWith(50);
        runWith(100);
        runWith(200);
        runWith(500);
        runWith(1000);
        //runWith(10000);
    }

    private void runWith(int poolSize) throws InterruptedException {

        if (poolSize < 1) {
            log.warn("must have at least one thread");
            return;
        }

        long start = System.nanoTime();
        for (int run = 0; run < 10; run++) { //run 10 times and take the average
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            List<MyThread> threads = new ArrayList<>();
            for (int i = 1; i <= poolSize; i++) {
                MyThread thread = new MyThread(TOTAL_COUNT / poolSize, readable_input);
                threads.add(thread);
            }

            for (MyThread thread : threads){
                executor.submit(thread);
            }

            executor.shutdown();
            executor.awaitTermination(100, TimeUnit.SECONDS);
        }
        //System.gc();
        long end = System.nanoTime();
        log.info(String.format("Pool size %d : %d ms", poolSize, (end-start) / 10 / 1000000 ));
    }
}
