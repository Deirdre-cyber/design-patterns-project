package OOPVersion;

import java.util.Arrays;

public class Leaderboard extends Player{

    private Player[] leaderboard;

    public Leaderboard(String player, int numberWins) {
        super(player, numberWins);
        setLeaderboard(leaderboard);
    }

    public Player[] getLeaderboard() {
        return Arrays.copyOf(leaderboard, leaderboard.length);
    }
    public void setLeaderboard(Player[] leaderboard) {
        if(leaderboard != null)
            this.leaderboard = Arrays.copyOf(leaderboard, leaderboard.length);
    }

    @Override
    public String toString() {

        String leaderList="";

        for(int i = 0; i < leaderboard.length; i++){
            leaderList += getLeaderboard()[i] + "\n";
        }

        return "Leaderboard" + leaderList;
    }
}