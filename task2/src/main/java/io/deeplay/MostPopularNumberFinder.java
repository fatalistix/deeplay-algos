package io.deeplay;

import java.util.HashMap;
import java.util.Map;

public class MostPopularNumberFinder {

    public int[] findMostPopularNumbers(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int maxCount = 0;

        for (int number : array) {
            if (map.containsKey(number)) {
                int count = map.get(number);
                map.put(number, count + 1);
                maxCount = Math.max(maxCount, count);
            } else {
                map.put(number, 1);
                maxCount = Math.max(maxCount, 1);
            }
        }

        // variable in lambda must be final or efficient final
        final int finalMaxCount = maxCount;

        return map.entrySet().stream()
                .filter(e -> e.getValue() == finalMaxCount)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
