package OOPVersion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Respresents a Player invoved in a Game.
 * A player can play multiple games*/

public class Player{

    //Mental Block converting from procedural console to OOP GUI - starting over.......

    private String name;
    private static int wins = 0;
    private char[] code;
    private boolean playerTurn = false;

    public Player(String name, int wins, char[] code, boolean playerTurn) {
        setName(name);
        setWins(wins);
        setCode(code);
        setPlayerTurn(playerTurn);
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public char[] getCode() {

        char[] allColours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        char colour;
        char[] s = new char[4];

        if(!getName().equals("Computer")){
            JOptionPane.showMessageDialog(null, "Your turn " + getName());
            playerTurn = true;

            for(int i = 0; i < 4; i++){

                colour = JOptionPane.showInputDialog("Please enter colour " + (i + 1) + " ").charAt(0);

                while(!validateColour(colour) || !Character.isLetter(colour)){
                    colour = JOptionPane.showInputDialog("INVALID!!! Please enter colour " + (i + 1) + " ").charAt(0);
                }
                s[i] = colour;
                code = s;
            }
        }
        else{
            for(int i = 0; i < 4; i++){

                int num = (int) (Math.random() * 4);
                s[i] = allColours[num];
                code = s;
            }
        }
        return code;
    }

    public void setCode(char[] code) {
        if(code != null)
            this.code = Arrays.copyOf(code, code.length);
    }

    public int getWins() {
        return wins;
    }

    public static void setWins(int wins) {
        if(wins > 0)
            Player.wins++;
        else
            Player.wins = wins;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player name: " + getName();
    }

//methods for Player Objects

    /*public char[] createCode(String player){
        char[] allColours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        char colour;
        char[] s = new char[4];

        if(!getName().equals("Computer")){
            JOptionPane.showMessageDialog(null, "Your turn " + player);

            for(int i = 0; i < 4; i++){

                colour = JOptionPane.showInputDialog("Please enter colour " + (i + 1) + " ").charAt(0);

                while(!validateColour(colour) || !Character.isLetter(colour)){
                    colour = JOptionPane.showInputDialog("INVALID!!! Please enter colour " + (i + 1) + " ").charAt(0);
                }
                s[i] = colour;
            }
        }
        else{
            for(int i = 0; i < 4; i++){

                int num = (int) (Math.random() * 4);
                //where to put the colours?
                s[i] = allColours[num];
            }
        }
        return s;
    }*/

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
    }

}
