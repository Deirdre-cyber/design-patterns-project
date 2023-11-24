package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void testGameStart() {
        Player playerOne = Mockito.mock(Player.class);
        Player playerTwo = Mockito.mock(Player.class);
        GameMode gameMode = Mockito.mock(GameMode.class);
        Leaderboard leaderboard = Mockito.mock(Leaderboard.class);

        Game game = new Game(playerOne, playerTwo, gameMode, leaderboard);

        when(gameMode.isGameFinished()).thenReturn(true);

        game.start();

        verify(gameMode, times(1)).startGame();
        verify(gameMode, times(1)).displayResult();
        verify(leaderboard, times(1)).update(playerOne, playerTwo);
    }
}
