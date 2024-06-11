package io.deeplay;

public record GameStatistics(
        double firstPlayerWinProbability,
        double secondPlayerWinProbability,
        double drawProbability
) {
}
