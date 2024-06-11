package io.deeplay;

import java.util.HashMap;
import java.util.Map;

public class MostPopularNumberFinder {

    public int[] findMostPopularNumbers(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int maxCount = 0;

        for (int number : array) {
            if (map.containsKey(number)) {
                int newCount = map.get(number) + 1;
                map.put(number, newCount);
                maxCount = Math.max(maxCount, newCount);
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
