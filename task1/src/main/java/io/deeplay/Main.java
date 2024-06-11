package io.deeplay;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];

        ArrayRandomFiller arrayRandomFiller = new ArrayRandomFiller();
        ArrayPrinter arrayPrinter = new ArrayPrinter();
        ArrayReorderer arrayReorderer = new ArrayReorderer();

        arrayRandomFiller.fillWithRandomNumbers(array, -2, 2);

        arrayPrinter.printArray(array);

        arrayReorderer.reorderArray(array);

        arrayPrinter.printArray(array);
    }
}