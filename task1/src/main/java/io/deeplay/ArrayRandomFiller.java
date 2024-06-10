package io.deeplay;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayRandomFiller {

    public void fillWithRandomNumbers(int[] array, int min, int max) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
    }
}
