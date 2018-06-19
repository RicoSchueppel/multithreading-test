package de.davitec.multithreading;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MTCmdLineRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        MTTest t = new MTTest();
    }
}
