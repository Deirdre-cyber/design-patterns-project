package OOPVersion;

import java.io.File;

public class Leaderboard {

    private Player[] leaders;

    public Leaderboard(Player[] leaders) {
        setLeaders(leaders);
    }

    public Player[] getLeaders() {
        return leaders;
    }
    public void setLeaders(Player[] leaders) {
        this.leaders = leaders;
    }
}
