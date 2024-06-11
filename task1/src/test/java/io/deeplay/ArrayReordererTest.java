package io.deeplay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class ArrayReordererTest {

    private final ArrayReorderer arrayReorderer = new ArrayReorderer();
    private final ArrayRandomFiller arrayRandomFiller = new ArrayRandomFiller();

    @Test
    public void givenEmptyArray_whenReorderArray_thenArrayIsNotChanged() {
        int[] array = new int[]{};
        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertEquals(array.length, 0);
    }

    @Test
    public void givenOneElementArray_whenReorderArray_thenArrayIsNotChanged() {
        int[] array = new int[]{1};
        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertEquals(array[0], 1);
    }

    @Test
    public void givenArrayWithOddPositiveAscendingNumbers_whenReorderArray_thenArrayIsNotChanged() {
        int[] array = new int[]{1, 3, 5, 7, 9, 11};
        int[] originalArray = Arrays.copyOf(array, array.length);

        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertArrayEquals(array, originalArray);
    }

    @Test
    public void givenArrayWithOddAscendingNumbers_whenReorderArray_thenArrayIsNotChanged() {
        int[] array = new int[]{-11, -9, -1, 1, 3, 5, 7, 9, 11};
        int[] originalArray = Arrays.copyOf(array, array.length);

        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertArrayEquals(array, originalArray);
    }

    @Test
    public void givenArrayOfZeros_whenReorderArray_thenArrayIsNotChanged() {
        int[] array = new int[]{0, 0, 0, 0, 0, 0};
        int[] originalArray = Arrays.copyOf(array, array.length);

        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertArrayEquals(array, originalArray);
    }

    @Test
    public void givenArrayWithOddDescendingNumbers_whenReorderArray_thenArrayHasOddAscendingNumbers() {
        int[] array = new int[]{11, 9, 7, 5, 3, 1, -1, -3, -5};
        Integer[] target = Arrays.stream(Arrays.copyOf(array, array.length))
                .boxed()
                .sorted()
                .toArray(Integer[]::new);

        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));
        Assertions.assertArrayEquals(Arrays.stream(array).boxed().toArray(Integer[]::new), target);
    }

    @Test
    public void givenArrayWithOddAndZerosNumbers_whenReorderArray_thenArrayHasOddAscendingNumbersAndZerosInTheEnd() {
        int[] array = new int[40];
        arrayRandomFiller.fillWithRandomNumbers(array, -20, 20);

        array = Arrays.stream(array).map(x -> 2 * x + x % 2).toArray();

        int zerosCount = Arrays.stream(array).map(x -> x == 0 ? 1 : 0).sum();
        int[] sortedOddNumbers = Arrays.stream(array).filter(x -> x % 2 != 0).sorted().toArray();

        final int[] finalArray = Arrays.copyOf(array, array.length);
        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(finalArray));

        Assertions.assertArrayEquals(sortedOddNumbers, Arrays.copyOf(finalArray, sortedOddNumbers.length));
        Assertions.assertEquals(zerosCount, Arrays.stream(finalArray).map(x -> x == 0 ? 1 : 0).sum());
    }

    @Test
    public void givenArrayWithZerosAndEvenNumbers_whenReorderArray_thenArrayHasZerosInTheBeginningAndEvenNumbersInTheEnd() {
        int[] array = new int[40];
        arrayRandomFiller.fillWithRandomNumbers(array, -20, 20);

        array = Arrays.stream(array).map(x -> 2 * x).toArray();

        int zerosCount = Arrays.stream(array).map(x -> x == 0 ? 1 : 0).sum();
        Integer[] sortedEvenNumbers = Arrays.stream(array)
                .filter(x -> x != 0)
                .boxed()
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);

        final int[] finalArray = Arrays.copyOf(array, array.length);
        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(finalArray));

        Assertions.assertArrayEquals(
                sortedEvenNumbers,
                Arrays.stream(Arrays.copyOfRange(finalArray, zerosCount, finalArray.length))
                        .boxed().toArray(Integer[]::new)
        );
        Assertions.assertEquals(zerosCount, Arrays.stream(finalArray).map(x -> x == 0 ? 1 : 0).sum());
    }

    @Test
    public void givenRandomNumbers_whenReorderArray_thenReorderedArray() {
        int[] array = new int[40];
        arrayRandomFiller.fillWithRandomNumbers(array, -20, 20);

        int zeroCount = Arrays.stream(array).map(x -> x == 0 ? 1 : 0).sum();
        Integer[] sortedEvenNumbers = Arrays.stream(array)
                .filter(x -> x != 0 && x % 2 == 0)
                .boxed()
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);
        Integer[] sortedOddNumbers = Arrays.stream(array)
                .filter(x -> x % 2 != 0)
                .boxed()
                .sorted()
                .toArray(Integer[]::new);

        Assertions.assertDoesNotThrow(() -> arrayReorderer.reorderArray(array));

        Assertions.assertEquals(zeroCount, Arrays.stream(array).map(x -> x == 0 ? 1 : 0).sum());
        Assertions.assertArrayEquals(
                sortedEvenNumbers,
                Arrays.stream(Arrays.copyOfRange(array, sortedOddNumbers.length + zeroCount, array.length))
                        .boxed().toArray(Integer[]::new)
        );
        Assertions.assertArrayEquals(
                sortedOddNumbers,
                Arrays.stream(Arrays.copyOfRange(array, 0, sortedOddNumbers.length))
                        .boxed().toArray(Integer[]::new)
        );
    }
}
