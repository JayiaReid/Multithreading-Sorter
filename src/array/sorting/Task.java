/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package array.sorting;

import java.util.Arrays;

/**
 *
 * @author jayia
 */
class Task implements Runnable {
    private final int[] array;
    private final int low;
    private final int high;
    private final int taskId;
    private final Progress tracker;

    public Task(Progress tracker, int taskId, int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.taskId = taskId;
        this.tracker = tracker;
    }

    @Override
    public void run() {
        int size = array.length / 10;

        for (int i = low; i < high; i += size) {
            Arrays.sort(array, i, Math.min(i + size, high));
            int progress = (int) (((double) (i - low) / (high - low)) * 100);
            tracker.updateProgress(taskId, progress);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        tracker.updateProgress(taskId, 100);
    }
}

