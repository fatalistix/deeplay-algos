package io.deeplay;

import java.util.Arrays;
import java.util.Collections;

public class ArrayReorderer {

    public void reorderArray(int[] array) {
        int lastOddPosition = -1;
        int firstEvenPosition = array.length;

        for (int verified = 0, i = 0; verified < array.length; ++verified) {
            if (array[i] % 2 == 0) {
                if (array[i] == 0) {
                    ++i;
                } else {
                    int temp = array[i];
                    array[i] = array[firstEvenPosition - 1];
                    array[firstEvenPosition - 1] = temp;
                    firstEvenPosition--;
                }
            } else {
                int temp = array[i];
                array[i] = array[lastOddPosition + 1];
                array[lastOddPosition + 1] = temp;
                lastOddPosition++;
                ++i;
            }
        }

        // Cannot sort int[] array in reverse order due to generic comparator function,
        // so we need to use boxed array (yes, it's not efficient)
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);

        Arrays.sort(boxedArray, 0, lastOddPosition + 1);
        Arrays.sort(boxedArray, firstEvenPosition, array.length, Collections.reverseOrder());

        for (int i = 0; i < array.length; i++) {
            array[i] = boxedArray[i];
        }
    }
}
