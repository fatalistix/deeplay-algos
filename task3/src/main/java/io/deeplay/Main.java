package io.deeplay;

public class Main {

    public static void main(String[] args) {
        int[] firstPlayerPattern = {4, 2, 4};
        int[] secondPlayerPattern = {4, 4, 4};

        Game game = new Game(firstPlayerPattern, secondPlayerPattern);
        GameStatistics statistics = game.countStatistics(100000, 1000);
        System.out.println(statistics);
        GameResult result = game.play(new int[]{1, 4, 2, 4, 4, 4, 4, 4, 2, 4,});
        System.out.println();
        System.out.println(result);
    }
}