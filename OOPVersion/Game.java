package OOPVersion;

import java.util.Arrays;

public class Game{

    private static Player[] players;
    private static int numberGames;
    private static int numberGuesses;
    private static String version;

    public Game(Player[] players, int numberGames, int numberGuesses, String version) {
        setPlayers(players);
        setNumberGames(numberGames);
        setNumberGuesses(numberGuesses);
        setVersion(version);
    }

    public static Player[] getPlayers() {
        return Arrays.copyOf(players, players.length);
    }

    public static void setPlayers(Player[] players) {
        if(players != null)
            players = Arrays.copyOf(players, players.length);
    }

    public static int getNumberGames() {
        return numberGames;
    }

    public static void setNumberGames(int numberGames) {
        Game.numberGames = numberGames;
    }

    public static int getNumberGuesses() {
        return numberGuesses;
    }

    public static void setNumberGuesses(int numberGuesses) {
        Game.numberGuesses = numberGuesses;
    }

    public static String getVersion() {
        return Game.version;
    }

    public void setVersion(String version) {
        Game.version = version;
    }

    @Override
    public String toString() {

        String player = "";

        for(int i = 0; i < players.length; i++){
            player += getPlayers()[i] + "";
        }


        return "\nPlayers: " + player + "\nNumber of Games: " + getNumberGames() + "\nNumber of Guesses: " + getNumberGuesses() + "\nVersion: " + getVersion();
    }
}