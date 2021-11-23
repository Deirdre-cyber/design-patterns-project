package OOPVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VersionClassic extends Game{

    private char[] solution = new char[4];


    public VersionClassic(){
        super("Classic Version - No data");
    }
    public VersionClassic(char[] solution) {
        super("Classic Version");
        setSolution(solution);
    }

    public char[] getSolution() {
        return Arrays.copyOf(solution, solution.length);
    }

    public void setSolution(char[] solution) {
        if(solution != null)
            this.solution = solution;
    }

    @Override
    public String toString() {
        return "Classic Game";
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

    public int[] compareCode(char[] g, char[] s){ //reuse for expert with added extra colour

        int [] h = {0, 0, 0, 0};
        int x, y;

        for(x = 0; x < s.length; x++){
            for(y = 0; y < g.length; y++){
                if(s[x] == g[y]){
                    if(x == y){
                        h[y] = 1;
                        g[x] = 'X';
                        break;
                    }
                    else if (y == 4){
                        h[y-1] = 2;
                    }
                }
            }
        }
        return h;
    }
}
