package prs.inmemory.logging.impl;

import prs.inmemory.logging.core.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogger implements Logger {
    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private final Thread worker;

    public AsyncLogger() {
        worker = new Thread(() -> {
            while (true) {
                try {
                    String log = logQueue.take();
                    System.out.println(log);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        worker.start();
    }

    @Override
    public void log(String message) {
        logQueue.offer(message);
    }
}