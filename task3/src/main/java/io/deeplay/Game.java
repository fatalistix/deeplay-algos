package io.deeplay;

public class Game {

    private final FixedCyclicBuffer firstPlayerBuffer;
    private final FixedCyclicBuffer secondPlayerBuffer;

    private final int[] firstPlayerPattern;
    private final int[] secondPlayerPattern;

    public Game(int[] firstPlayerPattern, int[] secondPlayerPattern) {
        this.firstPlayerPattern = firstPlayerPattern;
        this.secondPlayerPattern = secondPlayerPattern;

        firstPlayerBuffer = new FixedCyclicBuffer(firstPlayerPattern.length);
        secondPlayerBuffer = new FixedCyclicBuffer(secondPlayerPattern.length);
    }

    public GameResult play(int[] sequence) {
        int firstPlayerScore = 0;
        int secondPlayerScore = 0;

        firstPlayerBuffer.fill(-1);
        secondPlayerBuffer.fill(-1);

        for (int s : sequence) {
            firstPlayerBuffer.add(s);
            secondPlayerBuffer.add(s);

            if (firstPlayerBuffer.checkEquals(firstPlayerPattern)) {
                firstPlayerScore++;
                firstPlayerBuffer.fill(-1);
            }
            if (secondPlayerBuffer.checkEquals(secondPlayerPattern)) {
                secondPlayerScore++;
                secondPlayerBuffer.fill(-1);
            }
        }

        return new GameResult(firstPlayerScore, secondPlayerScore);
    }

    public GameStatistics countStatistics(int epochs, int sequenceSize) {
        int firstPlayerWinCount = 0;
        int secondPlayerWinCount = 0;
        int drawCount = 0;

        RandomSequenceGenerator generator = new RandomSequenceGenerator();

        for (int i = 0; i < epochs; ++i) {
            GameResult gameResult = play(generator.generateSequence(sequenceSize));
            if (gameResult.firstPlayerScore() > gameResult.secondPlayerScore()) {
                firstPlayerWinCount++;
            } else if (gameResult.firstPlayerScore() < gameResult.secondPlayerScore()) {
                secondPlayerWinCount++;
            } else {
                drawCount++;
            }
        }

        return new GameStatistics(
                (double) firstPlayerWinCount / epochs,
                (double) secondPlayerWinCount / epochs,
                (double) drawCount / epochs
        );
    }
}
