package DPSystem;

import java.util.Scanner;

enum PlayerType {
    HUMAN,
    COMPUTER
}

public class Player {
    private String name;
    private PlayerType playerType;
    private GameMode gameMode;
    private int wins;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getWins() {
        return wins;
    }

    public void makeMove(GameMode gameMode, boolean isMultiplayer, Player opponent) {
        if (isMultiplayer && playerType == PlayerType.HUMAN) {
            Scanner scanner = new Scanner(System.in);
            char[] guess = new char[4];

            for (int i = 0; i < 4; i++) {
                guess[i] = scanner.next().charAt(0);
            }
            
            gameMode.placeMove(guess);
        } else if (isMultiplayer && playerType == PlayerType.COMPUTER) {
            char[] guess = generateRandomMove();
            gameMode.placeMove(guess);

        } else if (!isMultiplayer && playerType == PlayerType.HUMAN) {
            Scanner scanner = new Scanner(System.in);
            char[] guess = new char[4];

            for (int i = 0; i < 4; i++) {
                guess[i] = scanner.next().charAt(0);
            }

            gameMode.placeMove(guess);

        } else if (!isMultiplayer && playerType == PlayerType.HUMAN) {
            char[] guess = generateRandomMove();
            gameMode.placeMove(guess);
        }
    }

    private char[] generateRandomMove() {
        char[] colors = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        char[] code = new char[4];

        for (int i = 0; i < code.length; i++) {
            int randomIndex = (int) (Math.random() * colors.length);
            code[i] = colors[randomIndex];
        }
        return code;
    }

}
