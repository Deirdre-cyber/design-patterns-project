package DPSystem.mastermind.src.main.java.com.example;

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

        // while (!gameMode.isGameFinished()) {
        //     playerOne.makeMove();
        //     displayMoves(playerOne, playerTwo);
        //     if (!gameMode.isGameFinished()) {
        //         playerTwo.makeMove();
        //         displayMoves(playerTwo, playerOne);
        //     }
        //     gameMode.updateTurn();
        // }

        gameMode.displayResult();
        leaderboard.update(playerOne, playerTwo);
    }

    // private void displayMoves(Player currentPlayer, Player opponentPlayer) {
    //     System.out.println(currentPlayer.getName() + "'s move:");

    // }
}
