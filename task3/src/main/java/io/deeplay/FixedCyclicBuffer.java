package io.deeplay;

import java.util.Arrays;

public class FixedCyclicBuffer {

    private final int[] buffer;
    private int head;

    public FixedCyclicBuffer(int size) {
        buffer = new int[size];
        head = 0;
    }

    public void add(int value) {
        buffer[head] = value;
        head = (head + 1) % buffer.length;
    }

    public void fill(int value) {
        Arrays.fill(buffer, value);
    }

    public boolean checkEquals(int[] array) {
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[(head + i) % buffer.length] != array[i]) {
                return false;
            }
        }
        return true;
    }
}
