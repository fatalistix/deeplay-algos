package io.deeplay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void givenSameSequences_whenPlay_thenSameScore() {
        int[] seq = new int[]{4, 2, 4};

        Game game = new Game(seq, seq);

        GameResult result = game.play(new int[]{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 1});
        Assertions.assertEquals(result.firstPlayerScore(), result.secondPlayerScore());
        Assertions.assertEquals(3, result.firstPlayerScore());
    }

    private record GameCase(
            int[] firstPlayerSequence,
            int[] secondPlayerSequence,
            int[] gameSequence,
            int firstPlayerScore,
            int secondPlayerScore
    ) {}

    @Test
    public void givenExampleSequences_whenPlay_thenExampleScores() {
        GameCase[] gameCases = new GameCase[]{
                new GameCase(
                        new int[]{4, 2, 4},
                        new int[]{4, 4, 4},
                        new int[]{1, 4, 2, 4, 4, 4, 4, 4, 2, 4},
                        2, 1
                ),
                new GameCase(
                        new int[]{4, 2, 4},
                        new int[]{4, 4, 4},
                        new int[]{1, 4, 2, 4, 4, 4, 4, 4, 4, 4},
                        1, 2
                ),
                new GameCase(
                        new int[]{4, 2, 4},
                        new int[]{2, 4, 2},
                        new int[]{4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 1},
                        3, 2
                ),
                new GameCase(
                        new int[]{1, 2, 3},
                        new int[]{2, 3, 1},
                        new int[]{2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1},
                        3, 4
                ),
                new GameCase(
                        new int[]{1, 2, 3},
                        new int[]{4, 5, 5},
                        new int[]{1, 2, 4, 3, 5, 5, 2, 3, 4, 5, 5, 5},
                        0, 1
                )
        };

        for (GameCase gc : gameCases) {
            Game game = new Game(gc.firstPlayerSequence, gc.secondPlayerSequence);
            GameResult result = game.play(gc.gameSequence);
            Assertions.assertEquals(gc.firstPlayerScore, result.firstPlayerScore());
            Assertions.assertEquals(gc.secondPlayerScore, result.secondPlayerScore());
        }
    }

    @Test
    public void givenEmptySequence_whenPlay_thenScoreIsZero() {
        Game game = new Game(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        GameResult result = game.play(new int[]{});
        Assertions.assertEquals(0, result.firstPlayerScore());
        Assertions.assertEquals(0, result.secondPlayerScore());
    }
}
