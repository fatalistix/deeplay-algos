package io.deeplay;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];

        ArrayRandomFiller arrayRandomFiller = new ArrayRandomFiller();
        ArrayPrinter arrayPrinter = new ArrayPrinter();
        MostPopularNumberFinder mostPopularNumberFinder = new MostPopularNumberFinder();

        arrayRandomFiller.fillWithRandomNumbers(array, -2, 2);

        arrayPrinter.printArray(array);

        int[] mostPopularNumbers = mostPopularNumberFinder.findMostPopularNumbers(array);

        arrayPrinter.printArray(mostPopularNumbers);
    }
}