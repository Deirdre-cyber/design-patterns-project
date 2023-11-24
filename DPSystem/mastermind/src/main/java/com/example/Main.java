
package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = null;

        while (game == null) {
            try {
                displayWelcomeMessage();

                GameDifficulty gameDifficulty = chooseGameDifficulty(scanner);

                String playerOneName = choosePlayerName(scanner);
                Player playerOne = new Player(playerOneName, PlayerType.HUMAN);
                System.out.println("-----------------------------------");
                String playerTwoName = generatePlayerName();
                Player playerTwo = new Player(playerTwoName, PlayerType.COMPUTER);

                GameMode gameMode = new GameMode(gameDifficulty, playerOne, playerTwo);
                
                playerOne.setGameMode(gameMode);
                playerTwo.setGameMode(gameMode);
                System.out.println("Let's play " + playerOne.getName() + " and " + playerTwo.getName() + "!");
                System.out.println("-----------------------------------");

                Leaderboard leaderboard = new Leaderboard();

                game = new Game(playerOne, playerTwo, gameMode, leaderboard);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid game difficulty.");
            }
        }

        game.start();
    }

    private static GameDifficulty chooseGameDifficulty(Scanner scanner) {
        System.out.println("Enter game difficulty ((CH)ILDREN, (CL)ASSIC, (E)XPERT):");
        String input = scanner.nextLine().toUpperCase();
        System.out.println("-----------------------------------");
        GameDifficulty gameDifficulty;

        switch (input) {
            case "CH":
                gameDifficulty = GameDifficulty.CHILDREN;
                break;
            case "CL":
                gameDifficulty = GameDifficulty.CLASSIC;
                break;
            case "E":
                gameDifficulty = GameDifficulty.EXPERT;
                break;
            default:
                System.out.println("Invalid input. Defaulting to CLASSIC.");
                gameDifficulty = GameDifficulty.CLASSIC;
                break;
        }
        return gameDifficulty;
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to");
        System.out.println("   _____                   __                       .__            .___");
        System.out.println("  /     \\ _____    _______/  |_  ___________  _____ |__| ____    __| _/");
        System.out.println(" /  \\ /  \\__  \\  /  ___/\\   __\\/ __ \\_  __ \\/     \\|  |/    \\  / __ | ");
        System.out.println("/    Y    \\/ __ \\_\\___ \\  |  | \\  ___/|  | \\/  Y Y  \\  |   |  \\/ /_/ | ");
        System.out.println("\\____|__  (____  /____  > |__|  \\___  >__|  |__|_|  /__|___|  /\\____ | ");
        System.out.println("        \\/     \\/     \\/            \\/            \\/        \\/      \\/");
    }

    public static String choosePlayerName(Scanner scanner) {
        System.out.println("Enter player one name:");
        return scanner.nextLine();
    }

    public static String generatePlayerName() {
        String[] names = { "John", "Jane", "Bob", "Alice", "Jack", "Jill", "Bill", "Ben", "Sam", "Sally" };
        int randomIndex = (int) (Math.random() * names.length);
        return names[randomIndex].toUpperCase();
    }
}
