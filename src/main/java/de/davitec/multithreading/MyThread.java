package de.davitec.multithreading;

public class MyThread extends Thread {

    private long count_to;
    private long result;

    MyThread(long count_to){
        this.count_to = count_to;
    }

    public void run() {
        int count = 0;
        for (int i = 0; i < count_to; i++) {
            count += i;
        }
        result = count;
    }

    public long getResult() {
        return result;
    }
}
