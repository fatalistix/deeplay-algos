package io.deeplay;

import java.util.concurrent.ThreadLocalRandom;

public class RandomSequenceGenerator {

    public int[] generateSequence(int size) {
        return ThreadLocalRandom.current().ints(size, 1, 7).toArray();
    }
}
