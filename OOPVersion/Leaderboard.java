package OOPVersion;

import java.util.Arrays;

public class Leaderboard extends Game{

    private Player[] leaders;

    public Leaderboard(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
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