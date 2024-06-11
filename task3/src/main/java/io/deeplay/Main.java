package io.deeplay;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputSequenceReader inputSequenceReader = new InputSequenceReader();

            System.out.println("Введите последовательность из 3-х чисел для первого игрока:");
            System.out.print("-> ");

            int[] firstPlayerSequence;
            try {
                firstPlayerSequence = inputSequenceReader.readSequence(3, scanner);
            } catch (InvalidSequenceException e) {
                System.out.println("Некорректная последовательность: " + e.getMessage());
                return;
            }

            System.out.println("Введите последовательность из 3-х чисел для второго игрока:");
            System.out.print("-> ");

            int[] secondPlayerSequence;
            try {
                secondPlayerSequence = inputSequenceReader.readSequence(3, scanner);
            } catch (InvalidSequenceException e) {
                System.out.println("Некорректная последовательность: " + e.getMessage());
                return;
            }

            System.out.println("Введите количество бросков кубика: ");
            System.out.print("-> ");

            int diceThrows;
            try {
                diceThrows = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некорректное количество бросков: " + e.getMessage());
                return;
            }

            if (diceThrows < 1) {
                System.out.println("Некорректное количество бросков: " + diceThrows);
                return;
            }

            Game game = new Game(firstPlayerSequence, secondPlayerSequence);

            System.out.println("Хотите узнать вероятности победы первого и второго игрока? (y/n)");
            System.out.print("-> ");

            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.trim().equals("y")) {
                System.out.println("Подождите, расчёт выполняется...");

                GameStatistics statistics = game.countStatistics(10000, 100);

                System.out.println("Вероятность победы первого игрока: " + statistics.firstPlayerWinProbability());
                System.out.println("Вероятность победы второго игрока: " + statistics.secondPlayerWinProbability());
                System.out.println("Вероятность ничьи: " + statistics.drawProbability());
            }

            System.out.println("Введите вашу последовательность:");
            System.out.print("-> ");

            int[] sequence;
            try {
                sequence = inputSequenceReader.readSequence(diceThrows, scanner);
            } catch (InvalidSequenceException e) {
                System.out.println("Некорректная последовательность: " + e.getMessage());
                return;
            }

            GameResult gameResult = game.play(sequence);
            System.out.println("Счет первого игрока: " + gameResult.firstPlayerScore());
            System.out.println("Счет второго игрока: " + gameResult.secondPlayerScore());

            if (gameResult.firstPlayerScore() > gameResult.secondPlayerScore()) {
                System.out.println("Победил первый игрок");
            } else if (gameResult.firstPlayerScore() < gameResult.secondPlayerScore()) {
                System.out.println("Победил второй игрок");
            } else {
                System.out.println("Ничья");
            }

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}