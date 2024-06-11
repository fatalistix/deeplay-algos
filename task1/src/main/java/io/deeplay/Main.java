package io.deeplay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите размер массива:");
            System.out.print("-> ");

            int arraySize = scanner.nextInt();
            int[] array = new int[arraySize];

            ArrayRandomFiller arrayRandomFiller = new ArrayRandomFiller();
            ArrayPrinter arrayPrinter = new ArrayPrinter();
            ArrayReorderer arrayReorderer = new ArrayReorderer();

            System.out.println("Введите через пробел левую и правую границы диапазона случайных чисел:");
            System.out.print("-> ");

            int leftBound = scanner.nextInt();
            int rightBound = scanner.nextInt();

            arrayRandomFiller.fillWithRandomNumbers(array, leftBound, rightBound);

            System.out.println("Массив был заполнен следующими числами:");
            arrayPrinter.printArray(array);

            arrayReorderer.reorderArray(array);

            System.out.println("Массив переупорядочен:");
            arrayPrinter.printArray(array);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}