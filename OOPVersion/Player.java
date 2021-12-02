package OOPVersion;

/**
 * Respresents a Player involved in a Game.
 * A player can play multiple games*/

public class Player{

    private String player;
    private static int numberWins; //number of wins per game


    public Player(String player) {
        setPlayer(player);
    }

    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }

    public int getNumberWins() {
        return numberWins;
    }
    public void setNumberWins(int numberWins) {
        Player.numberWins = numberWins;
    }

    @Override
    public String toString() {
        return "\nPlayer: " + getPlayer() + "\nTotal Wins: " + getNumberWins();
    }
}