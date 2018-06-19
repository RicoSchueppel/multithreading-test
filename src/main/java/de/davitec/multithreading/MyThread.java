package de.davitec.multithreading;

import java.util.List;

public class MyThread extends Thread {

    private long count_to;
    private String result;
    List<String> input;

    MyThread(long count_to, List<String> input){
        this.count_to = count_to;
        this.input = input;
    }

    public void run() {
        for (int i = 0; i < count_to; i++) {
            result += input.get(i % 100);
        }
    }

    public String getResult() {
        return result;
    }
}
