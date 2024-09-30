/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package array.sorting;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author jayia
 */
public class Monitor implements Runnable{
     private final ExecutorService executor;
    private final Progress tracker;

    public Monitor(ExecutorService executor, Progress tracker) {
        this.executor = executor;
        this.tracker = tracker;
    }

    private static void printProgress(Progress tracker) {
        StringBuilder progress = new StringBuilder();
        tracker.getProgressMap().forEach((taskId, taskProgress) -> {
            progress.append("Task ").append(taskId).append(": [")
                    .append("=".repeat(taskProgress / 10))
                    .append(" ".repeat(10 - taskProgress / 10))
                    .append("] ").append(taskProgress).append("%\n");
        });
        System.out.println("\n" + progress);
    }

    @Override
    public void run() {
        while (!executor.isTerminated()) {
            printProgress(tracker);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
