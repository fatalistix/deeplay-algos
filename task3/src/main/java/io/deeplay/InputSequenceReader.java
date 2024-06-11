package io.deeplay;

import java.util.Scanner;

public class InputSequenceReader {

    public int[] readSequence(int size, Scanner scanner) throws InvalidSequenceException {
        int[] sequence = new int[size];
        for (int i = 0; i < size; ++i) {
            sequence[i] = scanner.nextInt();
            if (sequence[i] < 1 || sequence[i] > 6) {
                throw new InvalidSequenceException("Введено некорректное число");
            }
        }
        return sequence;
    }
}
