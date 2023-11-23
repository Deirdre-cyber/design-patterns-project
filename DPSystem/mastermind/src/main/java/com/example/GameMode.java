package DPSystem.mastermind.src.main.java.com.example;

import java.util.Arrays;
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
    private int numGames;
    private int numGuesses;
    private int currentTurn;
    private GameDifficulty difficulty;

    public GameMode(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        this.name = difficulty.toString();
        this.gameIsWon = false;
        this.currentTurn = 1;
        this.movesLeft = 10;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting game mode: " + name);

        System.out.println("Enter the number of games (1 - 10):"); 
        this.numGames = scanner.nextInt();
        System.out.println("Enter the number of guesses (1 - 10):");
        this.numGuesses = scanner.nextInt();

        for (int i = 0; i < numGames && !isGameFinished(); i++) {
            int gameCount = i + 1;
            System.out.println("Starting game: " + gameCount);

            initializeSolution(difficulty);

            for (int j = 0; j < numGuesses && !isGameFinished(); j++) {
                System.out.println("Turn: " + (j + 1));

                makeMove();

                if (gameIsWon) {
                    break;
                }
            }

            System.out.println("Game Over");
        }
    }

    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        char[] guess = new char[4];

        System.out.println("Enter your guess (4 characters): ");
        for (int i = 0; i < 4; i++) {
            guess[i] = scanner.next().charAt(0);
        }
        boolean[] hints = compareCode(guess, solution);
        displayGuessesAndHints(guess, hints);
        placeMove(guess);
    }

    private void displayGuessesAndHints(char[] guess, boolean[] hints) {
        System.out.print("Guess: ");
        for (char c : guess) {
            System.out.print(c + " ");
        }
    
        System.out.print("Hints: ");
        for (boolean hint : hints) {
            System.out.print(hint ? "O " : "X ");
        }
    
        System.out.println();
    }

    public void initializeSolution(GameDifficulty difficulty) {
        char[] colors;

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

        for (int i = 0; i < code.length; i++) {
            int randomIndex = (int) (Math.random() * colors.length);
            code[i] = colors[randomIndex];
        }

        return code;
    }

    public void placeMove(char[] guess) {
        boolean[] hints = compareCode(guess, solution);

        if (checkWin(hints)) {
            gameIsWon = true;
            System.out.println("You won!");
        }

        movesLeft--;

        if (movesLeft == 0) {
            System.out.println("Game over!");
        }
    }

    public boolean[] compareCode(char[] guess, char[] solution) {
        boolean[] hints = new boolean[guess.length];

        char[] guessCopy = Arrays.copyOf(guess, guess.length);
        char[] solutionCopy = Arrays.copyOf(solution, solution.length);

        for (int i = 0; i < guessCopy.length; i++) {
            if (guessCopy[i] == solutionCopy[i]) {
                hints[i] = true;
                guessCopy[i] = 'X';
                solutionCopy[i] = 'X';
            }
        }

        for (int i = 0; i < guessCopy.length; i++) {
            for (int j = 0; j < solutionCopy.length; j++) {
                if (!hints[i] && guessCopy[i] == solutionCopy[j]) {
                    guessCopy[i] = 'X';
                    solutionCopy[j] = 'X';
                    break;
                }
            }
        }

        return hints;
    }

    public boolean checkWin(boolean[] hints) {
        for (boolean hint : hints) {
            if (!hint) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameFinished() {
        return false;
    }

    public void updateTurn() {
        currentTurn++;
    }

    public void displayResult() {
        if(isGameFinished()) {
            if (gameIsWon)  {
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("Game over. You lost!");
            }
        }
        else {
            if(movesLeft == 0) {
                System.out.println("Game over. You ran out of moves!");
            } else {
                System.out.println("Game is not finished yet!");
            }
        }
    }

    public int getMovesLeft() {
        return movesLeft;
    }
}
