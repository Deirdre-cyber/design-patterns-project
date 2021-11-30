package OOPVersion;

import java.io.File;
import java.util.Arrays;

public class Leaderboard extends Mastermind{

    private Player[] leaders;

    public Leaderboard(Player[] players, int numberGames, int numberGuesses, String version, Player[] leaders) {
        super(players, numberGames, numberGuesses, version);
        setLeaders(leaders);
    }

    public Player[] getLeaders() {
        return leaders;
    }
    public void setLeaders(Player[] leaders) {
        this.leaders = leaders;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "leaders=" + Arrays.toString(leaders) +
                '}';
    }
}
