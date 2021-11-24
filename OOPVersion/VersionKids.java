package OOPVersion;


public class VersionKids /*implements Game*/{

    private String version;
    private Player playerOne;
    private Player playerTwo;

    public VersionKids(String version, Player playerOne, Player playerTwo) {
        setVersion(version);
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        if(version.equals("kids"))
            this.version = "kids";//play kids....
        else
            this.version = version;
    }

    public Player getPlayerOne() {
        return playerOne;
    }
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }


}
