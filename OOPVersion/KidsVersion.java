package OOPVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KidsVersion extends Game{

    private char[] solution = new char[4];

    public KidsVersion(){
        super("Kids Version - No data");
    }

    public KidsVersion(char[] solution) {
        super("Kids Version");
        setSolution(solution);
    }

    public void setSolution(char[] solution) {
        if(solution != null)
            this.solution = solution;
    }
    public char[] getSolution(){
        return Arrays.copyOf(solution, solution.length);
    }

    @Override
    public String toString() {
        return "Guess: " + Arrays.toString(getGuess()) + "\t\tHints: " + Arrays.toString(getHints());
    }

    public char[] createCode(String play){
        //convert all to JOptionPane
        char colour;
        char[] s = new char[4];

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome " + play);

        for(int i = 0; i < 4; i++){

            System.out.println("Please enter colour " + (i + 1) + " ");
            colour = input.next().charAt(0);

            while(!validateColour(colour) || !Character.isLetter(colour)){
                System.out.println("Invalid colour. Please enter a valid colour for colour " + (i + 1) + " ");
                colour = input.next().charAt(0);
            }
            s[i] = colour;
        }
        return s;
    }

    public boolean validateColour(char c){
    //check colour chosen is in colour list
        char[] colours = getColours();
        ArrayList<Character> coloursList = new ArrayList<>();

        for (char colour : colours)
            coloursList.add(colour);

        for (Character ch : coloursList) {
            if (ch == c)
                return true;
        }
        return false;
    }

    public boolean compareCode(char[] g, char[] s){
        //sort array and use search to improve efficiency
        //if colours are all the same....??

        int count=0;

        for (char c : g) {
            for (char value : s)
                if (c == value)
                    count++;
        }
        return count == g.length;
    }
}
