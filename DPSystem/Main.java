
package DPSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = null;

        while (game == null) {
            try {
                System.out.println("Creating game mode...");
                System.out.println("Enter game difficulty (CHILDREN, CLASSIC, EXPERT):");

                GameDifficulty gameDifficulty = GameDifficulty.valueOf(scanner.nextLine().toUpperCase());
                GameMode gameMode = new GameMode(gameDifficulty);

                System.out.println("Enter player one name:");
                String playerOneName = scanner.nextLine();
                Player playerOne = new Player(playerOneName, PlayerType.HUMAN);

                System.out.println("Enter player two name:");
                String playerTwoName = scanner.nextLine();
                Player playerTwo = new Player(playerTwoName, PlayerType.COMPUTER);

                Leaderboard leaderboard = new Leaderboard();

                game = new Game(playerOne, playerTwo, gameMode, leaderboard);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid game difficulty.");
            }
        }

        game.start();
        scanner.close();
    }
}
