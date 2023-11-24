package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class GameModeTest {

    @Test
    void testInitializeSolution() {
        GameMode gameMode = new GameMode(GameDifficulty.CLASSIC, new Player("Player1", PlayerType.HUMAN), new Player("Player2", PlayerType.COMPUTER));
        gameMode.initializeSolution(GameDifficulty.CLASSIC);
        assertNotNull(gameMode.getSolution());
        assertEquals(4, gameMode.getSolution().length);
    }

    @Test
    void testCompareCode() {
        GameMode gameMode = new GameMode(GameDifficulty.CLASSIC, new Player("Player1", PlayerType.HUMAN), new Player("Player2", PlayerType.COMPUTER));
        gameMode.initializeSolution(GameDifficulty.CLASSIC);
        char[] solution = gameMode.getSolution();

        char[] correctGuess = Arrays.copyOf(solution, solution.length);
        char[] incorrectGuess = {'a', 'b', 'c', 'd'};

        String[] hintsCorrect = gameMode.compareCode(correctGuess, solution);
        String[] hintsIncorrect = gameMode.compareCode(incorrectGuess, solution);

        for (String hint : hintsCorrect) {
            assertEquals("O", hint);
        }

        for (String hint : hintsIncorrect) {
            assertNotEquals("O", hint);
        }
    }

    @Test
    void testCheckWin() {
        GameMode gameMode = new GameMode(GameDifficulty.CLASSIC, new Player("Player1", PlayerType.HUMAN), new Player("Player2", PlayerType.COMPUTER));
        gameMode.initializeSolution(GameDifficulty.CLASSIC);

        assertFalse(gameMode.checkWin(new String[]{"X", "_", "O", "X"}));
        assertTrue(gameMode.checkWin(new String[]{"O", "O", "O", "O"}));
    }

}
