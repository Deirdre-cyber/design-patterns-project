package DPSystem.mastermind.src.main.java.com.example;

import java.util.Arrays;
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
    private char[] lastMove;
    private String[] lastHints;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.wins = 0;
        this.lastMove = new char[4];
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

    public char[] getLastMove() {
        return lastMove;
    }
    
    public void setLastMove(char[] lastMove) {
        this.lastMove = lastMove;
    }

    public String[] getLastHints() {
        return lastHints;
    }

    public void setLastHints(String[] strings) {
        this.lastHints = strings;
    }

    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        char[] guess = new char[4];
    
        if (getPlayerType() == PlayerType.HUMAN) {
            System.out.println("Enter your guess (4 characters) " + getName() + ":");
            for (int i = 0; i < 4; i++) {
                guess[i] = scanner.next().charAt(0);
            }
        } else {
            guess = generateRandomMove();
        }
    
        setLastMove(guess);
        getGameMode().placeMove(this, guess);
    
        setLastHints(getGameMode().compareCode(guess, getGameMode().getSolution()));
        //getGameMode().displayGuessesAndHints(guess, getLastHints());
    }
    
    public char[] generateRandomMove() {
        char[] colors;
        
        switch (getGameMode().getDifficulty()) {
            case CHILDREN:
                colors = new char[]{'r', 'g', 'b', 'y'};
                break;
            case CLASSIC:
                colors = new char[]{'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
                break;
            case EXPERT:
                colors = new char[]{'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v', 'c', 'm'};
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }
    
        char[] code = new char[4];
    
        for (int i = 0; i < code.length; i++) {
            int randomIndex = (int) (Math.random() * colors.length);
            code[i] = colors[randomIndex];
        }
        return code;
    }
    


}
