package OOPVersion;

import java.util.Arrays;

public class Game{

    private Player[] players;    //stores two players
    private int numberGames;    //set from gui
    private int numberGuesses;  //set from gui
    private String version;     //set from gui
    //colours

    public Game(Player[] players, int numberGames, int numberGuesses, String version) {
        setPlayer(players);
        setNumberGames(numberGames);
        setNumberGuesses(numberGuesses);
        setVersion(version);
    }

    public Player[] getPlayer() {
        return Arrays.copyOf(players, players.length);
    }

    public void setPlayer(Player[] player) {
        if(player != null)
            this.players = Arrays.copyOf(player, player.length);
    }

    public int getNumberGames() {
        return numberGames;
    }

    public void setNumberGames(int numberGames) {
        this.numberGames = numberGames;
    }

    public int getNumberGuesses() {
        return numberGuesses;
    }

    public void setNumberGuesses(int numberGuesses) {
        this.numberGuesses = numberGuesses;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {

        String player = "";

        for(int i = 0; i < players.length; i++){
            player += getPlayer()[i] + "";
        }


        return "\nPlayers: " + player + "\nNumber of Games: " + getNumberGames() + "\nNumber of Guesses: " + getNumberGuesses() + "\nVersion: " + getVersion();
    }
}
