package com.example;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private GameMode gameMode;
    private Leaderboard leaderboard;

    public Game(Player playerOne, Player playerTwo, GameMode gameMode, Leaderboard leaderboard) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameMode = gameMode;
        this.leaderboard = leaderboard;
    }

    public void start() {
        gameMode.startGame();
        gameMode.displayResult();
        leaderboard.update(playerOne, playerTwo);
    }

}
