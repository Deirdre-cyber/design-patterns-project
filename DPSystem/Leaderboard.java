package DPSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private List<PlayerScore> scores;
    private static final String CSV_FILE_PATH = "leaderboard.csv";

    public Leaderboard() {
        this.scores = new ArrayList<>();
        loadLeaderboard();
    }

    public void update(Player playerOne, Player playerTwo) {
        if (playerOne.getPlayerType() == PlayerType.HUMAN && playerTwo.getPlayerType() == PlayerType.COMPUTER) {
            int score = calculateScore(playerOne, playerOne.getGameMode().getMovesLeft(),
                    playerOne.getGameMode().getDifficulty());
            scores.add(new PlayerScore(playerOne.getName(), score));
            Collections.sort(scores);
            displayLeaderboard();
            saveLeaderboard();
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
        System.out.println("Leaderboard:");
        System.out.println("Player\t\tScore");

        for (PlayerScore score : scores) {
            System.out.println(score.getPlayerName() + "\t\t" + score.getScore());
        }
    }

    private void loadLeaderboard() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String playerName = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                scores.add(new PlayerScore(playerName, score));
            }
        } catch (FileNotFoundException e) {
            createLeaderboardFile();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
        }
    }

    private void createLeaderboardFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write("Player,Score");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error creating leaderboard file: " + e.getMessage());
        }
    }

    private void saveLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write("Player,Score");
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
