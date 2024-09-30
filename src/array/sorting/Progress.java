/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package array.sorting;

/**
 *
 * @author jayia
 */
import java.util.concurrent.ConcurrentHashMap;

public class Progress {
    private final ConcurrentHashMap<Integer, Integer> progressMap = new ConcurrentHashMap<>();

    public void updateProgress(int taskId, int progress) {
        progressMap.put(taskId, progress);
    }

    public int getProgress(int taskId) {
        return progressMap.getOrDefault(taskId, 0);
    }

    public ConcurrentHashMap<Integer, Integer> getProgressMap() {
        return progressMap;
    }
}

