package array.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArraySorting {
    public static void main(String[] args) {
        int[] array = new int[200];

        ExecutorService executor = Executors.newCachedThreadPool();
        Lock lock = new ReentrantLock();
        int mid = array.length / 2;
        Progress tracker = new Progress();

        Generate generateTask = new Generate(array);
        executor.execute(generateTask);

        while (!generateTask.isComplete()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("--------Original Array---------");
        printArray(array, 20);

        executor.execute(new Task(tracker, 1, array, 0, mid + 1));
        executor.execute(new Task(tracker, 2, array, mid + 1, array.length));

        Thread monitorThread = new Thread(new Monitor(executor, tracker));
        monitorThread.start();

        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        lock.lock();
        try {
            int[] array2 = merge(array, mid);
            System.out.println("--------Merged Array---------");
            printArray(array2, 20);
        } finally {
            lock.unlock();
        }
    }

    static int[] merge(int[] array, int mid) {
        int[] leftArray = Arrays.copyOfRange(array, 0, mid + 1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, array.length);
        int[] sorted = new int[array.length];
        int j = 0, k = 0;

        for (int i = 0; i < array.length; i++) {
            if (j < leftArray.length && (k >= rightArray.length || leftArray[j] <= rightArray[k])) {
                sorted[i] = leftArray[j];
                j++;
            } else if (k < rightArray.length) {
                sorted[i] = rightArray[k];
                k++;
            }
        }

        return sorted;
    }
    
    static void printArray(int[] array, int size){
        for (int i = 0; i < array.length; i++) {
        System.out.printf("%d ", array[i]);
        if ((i + 1) % size == 0) {
            System.out.println();
        }
    }
        System.out.println(); 
    }
}




