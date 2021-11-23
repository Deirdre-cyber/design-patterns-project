package OOPVersion;

public interface Game {

    //Mental Block converting from procedural console to OOP GUI - starting over.......


    //Rethink all this
    void choosePlayers();
    void chooseGameVersion();
    char[] createCode(String play);
    boolean validateColour(char c);
    int[] compareCode(char[] g, char[] s);
    void updateLeaderboard();
}
