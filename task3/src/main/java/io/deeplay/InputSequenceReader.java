package io.deeplay;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputSequenceGenerator {

    public int[] generateSequence(int size) throws InvalidSequenceException {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] sequence = new int[size];
            for (int i = 0; i < size; ++i) {
                sequence[i] = scanner.nextInt();
                if (sequence[i] < 1 || sequence[i] > 6) {
                    throw new InvalidSequenceException("Введено некорректное число");
                }
            }
            return sequence;
        } catch (InputMismatchException e) {
            throw new InvalidSequenceException("Введено некорректное число");
        } catch (NoSuchElementException e) {
            throw new InvalidSequenceException("Недостаточно чисел для генерации последовательности");
        }
    }
}
