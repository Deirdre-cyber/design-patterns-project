package OOPVersion;

/**
 * Respresents a Player involved in a Game.
 * A player can play multiple games*/

public class Player /*extends Game*/{

    //FINISH
    private String player;
    private static int numberWins; //number of wins per game

    public Player() {
        this("Computer", 0);
    }

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

    public Player(String player, int numberWins) {
        setPlayer(player);
        setNumberWins(numberWins);
    }

    @Override
    public String toString() {
        return "Player: " + getPlayer() + "\nTotal Wins: " + getNumberWins();
    }

    //methods for Player Objects

    /*

    createCodeComputer....
    public static char[] createCodeComputerKids(String play) {

        int num;
        char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        char[] s = new char[4];

        System.out.println("Your turn " + play);

        for (int i = 0; i < 4; i++) {
            num = (int)(Math.random()*7);

            s[i] = colours[num];
        }
        return s;
    }

    public static char[] createCodeComputerExpert(String play) {

        int num;
        char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v', 'K'};
        char[] s = new char[4];

        System.out.println("Your turn " + play);

        for (int i = 0; i < 4; i++) {
            num = (int)(Math.random()*8);
            s[i] = colours[num];
        }
        return s;
    }


    public boolean validateColour(char c){

        //check colour chosen is in colour list

        ArrayList<Character> coloursList = new ArrayList<>();
        char[] allColours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};

        for (char colour : allColours)
            coloursList.add(colour);

        for (Character ch : coloursList) {
            if (ch == c)
                return true;
        }
        return false;
    }*/

}
