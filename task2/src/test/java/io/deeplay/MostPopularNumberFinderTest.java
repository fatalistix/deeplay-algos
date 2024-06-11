package io.deeplay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MostPopularNumberFinderTest {

    private final MostPopularNumberFinder mostPopularNumberFinder = new MostPopularNumberFinder();

    @Test
    public void givenEmptyArray_whenFindMostPopularNumber_thenEmptyArray() {
        int[] array = new int[]{};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 0);
    }

    @Test
    public void givenArrayWithOneNumber_whenFindMostPopularNumber_thenArrayWithOneNumber() {
        int[] array = new int[]{1};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 1);
        Assertions.assertEquals(result[0], 1);
    }

    @Test
    public void givenArrayWithOneNumberRepeating10Times_whenFindMostPopularNumber_thenArrayWithOneNumber() {
        int[] array = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 1);
        Assertions.assertEquals(result[0], 1);
    }

    @Test
    public void givenArrayWithTwoNumbersEachRepeating10Times_whenFindMostPopularNumber_thenArrayWithTwoNumbers() {
        int[] array = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 2);
        Assertions.assertEquals(Arrays.stream(result).map(x -> x == 1 ? 1 : 0).sum(), 1);
        Assertions.assertEquals(Arrays.stream(result).map(x -> x == 2 ? 1 : 0).sum(), 1);
    }

    @Test
    public void givenArrayWith10UniqueNumbers_whenFindMostPopularNumber_thenArrayWith10Numbers() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 10);
        Assertions.assertArrayEquals(
                Arrays.stream(array).sorted().toArray(),
                Arrays.stream(result).sorted().toArray()
        );
    }

    @Test
    public void givenArrayWith10UniqueNumbersOneDuplicate_whenFindMostPopularNumber_thenArrayWithOneNumber() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10};
        int[] result = mostPopularNumberFinder.findMostPopularNumbers(array);

        Assertions.assertEquals(result.length, 1);
        Assertions.assertEquals(result[0], 10);
    }
}
