/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package array.sorting;

import java.util.Random;

/**
 *
 * @author jayia
 */

class Generate implements Runnable {
    private int[] array;
    private boolean complete = false;

    public Generate(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }
}
