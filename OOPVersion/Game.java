package OOPVersion;

import java.util.Arrays;

public class Game /*extends GameBoardGUI*/{

    private Player[] player;
    private int numberGames;
    private int numberGuesses;
    private String version;

    public Game() {
        this(new Player[]{}, 0, 0, "Game Version not set");
    }

    public Game(Player[] player, int numberGames, int numberGuesses, String version) {
        setPlayer(player);
        setNumberGames(numberGames);
        setNumberGuesses(numberGuesses);
        setVersion(version);
    }

    public Player[] getPlayer() {
        return Arrays.copyOf(player, player.length);
    }
    public void setPlayer(Player[] player) {
        //if multi or single
        if(player != null) {
            this.player = Arrays.copyOf(player, player.length);

        }
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
        //example, not real code
        if(version.equals("kids"))
            this.version = "kids";
        else if(version.equals("Classic"))
            this.version = "Classic";
        else
            this.version = "Expert";
    }

    @Override
    public String toString() {

        return "Player one: " + Arrays.toString(getPlayer()) + "\nPLayer Two: " + Arrays.toString(getPlayer())
                + "\nGames Played: " + getNumberGames() + "\nGuesses Made: " + getNumberGuesses();
    }
}
