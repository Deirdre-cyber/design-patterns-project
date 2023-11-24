package DPSystem.mastermind.src.main.java.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

enum GameDifficulty {
    CHILDREN,
    CLASSIC,
    EXPERT
}

public class GameMode {
    private String name;
    private boolean gameIsWon;
    private int movesLeft;
    private char[] solution;
    private char[] colors;
    private int numGames;
    private int numGuesses;
    private int currentTurn;
    private GameDifficulty difficulty;
    private Player playerOne;
    private Player playerTwo;

    public GameMode(GameDifficulty difficulty, Player playerOne, Player playerTwo) {
        this.difficulty = difficulty;
        this.name = difficulty.toString();
        this.gameIsWon = false;
        this.currentTurn = 1;
        this.movesLeft = 0;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting game mode: " + name);
        System.out.println("-----------------------------------");
        do {
            System.out.println("Enter the number of games (1 - 10):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                scanner.next();
            }
            this.numGames = scanner.nextInt();
        } while (this.numGames < 1 || this.numGames > 10);
        System.out.println("-----------------------------------");
        do {
            System.out.println("Enter the number of guesses (1 - 10):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                scanner.next();
            }
            this.numGuesses = scanner.nextInt();
        } while (this.numGuesses < 1 || this.numGuesses > 10);
        System.out.println("-----------------------------------");
        movesLeft = numGuesses;

        for (int i = 0; i < numGames && !isGameFinished(); i++) {
            int gameCount = i + 1;
            System.out.println("Starting game: " + gameCount);

            initializeSolution(difficulty);

            for (int j = 0; j < numGuesses && !isGameFinished(); j++) {
                System.out.println("Turn: " + (j + 1));

                makeMove(playerOne);

                if (gameIsWon) {
                    break;
                }
            }
        }

    }

    public void makeMove(Player player) {
        char[] guess;
        String[] hints;
    
        if (player.getPlayerType() == PlayerType.HUMAN) {
            player.makeMove();
            guess = player.getLastMove();
            hints = compareCode(guess, solution);
            displayGuessesAndHints(guess, hints);
        } else {
            System.out.println("-----------------------------------");
            System.out.println("Computer " + player.getName()+ " is making a move...");
            guess = player.generateRandomMove();
            hints = compareCode(guess, solution);
            displayGuessesAndHints(guess, hints);
        }
    
        if (!isGameFinished()) {
            if (player == playerOne) {
                makeMove(playerTwo);
            } else {
                movesLeft--;
                updateTurn();
            }
        }
    }
    
    
    
    public void displayGuessesAndHints(char[] guess, String[] hints) {
        int boxWidth = 30;
    
        printBorder(boxWidth);
    
        printRow("Guess:", guess, boxWidth);
    
        printRow("Hints:", hints, boxWidth);
    
        printBorder(boxWidth);
    
        System.out.println("O = correct color, correct position");
        System.out.println("X = correct color, wrong position");
        System.out.println("_ = wrong color");
        System.out.println();
    }
    
    private void printRow(String label, char[] data, int boxWidth) {
        System.out.print("| " + label + " ");
        for (char c : data) {
            System.out.print(c + " ");
        }
    
        for (int i = label.length() + data.length * 2 + 2; i < boxWidth - 1; i++) {
            System.out.print(" ");
        }
    
        System.out.println("|");
    }
    
    private void printRow(String label, String[] data, int boxWidth) {
        System.out.print("| " + label + " ");
        for (String value : data) {
            System.out.print(value + " ");
        }
    
        for (int i = label.length() + data.length * 2 + 2; i < boxWidth - 1; i++) {
            System.out.print(" ");
        }
    
        System.out.println("|");
    }
    
    private void printBorder(int boxWidth) {
        for (int i = 0; i < boxWidth; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    

    public void initializeSolution(GameDifficulty difficulty) {
        switch (difficulty) {
            case CHILDREN:
                colors = new char[] { 'r', 'g', 'b', 'y' };
                break;
            case CLASSIC:
                colors = new char[] { 'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v' };
                break;
            case EXPERT:
                colors = new char[] { 'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v', 'c', 'm' };
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }

        solution = generateRandomSolution(colors);
    }

    private char[] generateRandomSolution(char[] colors) {
        char[] code = new char[4];

        List<Character> colorList = new ArrayList<>();
        for (char color : colors) {
            colorList.add(color);
        }

        for (int i = 0; i < code.length; i++) {
            int randomIndex = (int) (Math.random() * colorList.size());
            code[i] = colorList.remove(randomIndex);
        }

        return code;
    }

    public void placeMove(Player player, char[] guess) {

        String[] hints = compareCode(guess, solution);

        if (checkWin(hints)) {
            gameIsWon = true;
        }

        
        updateTurn();

    }

    public String[] compareCode(char[] guess, char[] solution) {
        String[] hints = new String[guess.length];
    
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == solution[i]) {
                hints[i] = "O";
            }
        }

        for (int i = 0; i < guess.length; i++) {
            if (hints[i] == null) {
                for (int j = 0; j < solution.length; j++) {
                    if (hints[j] == null && guess[i] == solution[j]) {
                        hints[j] = "X";
                    }
                }
            }
        }

        for (int i = 0; i < hints.length; i++) {
            if (hints[i] == null) {
                hints[i] = "_";
            }
        }
    
        return hints;
    }
    
    
    public boolean checkWin(String[] hints) {
        for (String hint : hints) {
            if (!hint.equals("O")) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameFinished() {
        return gameIsWon || movesLeft == 0;
    }

    public void updateTurn() {
        currentTurn++;
    }

    public void displayResult() {
        if (isGameFinished()) {
            if (gameIsWon) {
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("Game over. You lost!");
                if(!gameIsWon){
                    System.out.println("Correct answer was: " + Arrays.toString(solution));
                }
            }
        } else {
            System.out.println("Game is not finished yet!");
        }
    }

    public int getMovesLeft() {
        return movesLeft-1;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public char[] getSolution() {
        return solution;
    }

}