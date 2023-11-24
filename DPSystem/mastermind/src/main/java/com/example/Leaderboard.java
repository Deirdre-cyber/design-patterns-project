package DPSystem.mastermind.src.main.java.com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private List<PlayerScore> scores;
    private static final String CSV_HEADER = "Player,Score";
    private static final String CSV_FILE_PATH = "leaderboard.csv";


    public Leaderboard() {
        this.scores = new ArrayList<>();
        loadLeaderboard();
    }

    public void update(Player playerOne, Player playerTwo) {
        GameMode gameModeOne = playerOne.getGameMode();
        GameMode gameModeTwo = playerTwo.getGameMode();
    
        if (gameModeOne != null && gameModeTwo != null) {
            int movesLeftOne = gameModeOne.getMovesLeft();
            int movesLeftTwo = gameModeTwo.getMovesLeft();
    
            if (playerOne.getPlayerType() == PlayerType.HUMAN && playerTwo.getPlayerType() == PlayerType.COMPUTER) {
                int scoreOne = calculateScore(playerOne, movesLeftOne, gameModeOne.getDifficulty());
                int scoreTwo = calculateScore(playerTwo, movesLeftTwo, gameModeTwo.getDifficulty());
    
                scores.add(new PlayerScore(playerOne.getName(), scoreOne));
                scores.add(new PlayerScore(playerTwo.getName(), scoreTwo));
    
                Collections.sort(scores);
                displayLeaderboard();
                saveLeaderboard();
            }
        } else {
            System.out.println("GameMode is null for one or both players. Unable to update leaderboard.");
        }
    }

    private int calculateScore(Player player, int movesLeft, GameDifficulty difficulty) {

        int difficultyBonus = 0;
        int score = movesLeft * 1000;

        switch (difficulty) {
            case CHILDREN:
                difficultyBonus = 5;
                break;
            case CLASSIC:
                difficultyBonus = 10;
                break;
            case EXPERT:
                difficultyBonus = 15;
                break;
            default:
                break;
        }

        return score + difficultyBonus;
    }

    void displayLeaderboard() {
        int boxWidth = 30;
    
        printBorder(boxWidth);
    
        System.out.println("| Leaderboard:");
        System.out.println("| Player\t\tScore");
    
        for (PlayerScore score : scores) {
            printRow("| " + score.getPlayerName(), "\t\t" + score.getScore(), boxWidth);
        }
    
        printBorder(boxWidth);
    }
    
    private void printRow(String label, String value, int boxWidth) {
        System.out.print(label);
    
        for (int i = label.length(); i < boxWidth - value.length() - 1; i++) {
            System.out.print(" ");
        }
    
        System.out.println(value + "|");
    }
    
    private void printBorder(int boxWidth) {
        for (int i = 0; i < boxWidth; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    

    private void loadLeaderboard() {
        boolean skipFirstLine = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                String playerName = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                scores.add(new PlayerScore(playerName, score));
            }
        } catch (FileNotFoundException e) {
            createLeaderboardFile();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error loading leaderboard: " + e.getMessage());
        }
    }
    
    private void createLeaderboardFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write(CSV_HEADER);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error creating leaderboard file: " + e.getMessage());
        }
    }

    private void saveLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write(CSV_HEADER);
            writer.newLine();
            for (PlayerScore score : scores) {
                writer.write(score.getPlayerName() + "," + score.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }
    private static class PlayerScore implements Comparable<PlayerScore> {
        private String playerName;
        private int score;

        public PlayerScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(PlayerScore other) {
            return Integer.compare(other.score, this.score);
        }
    }
}
