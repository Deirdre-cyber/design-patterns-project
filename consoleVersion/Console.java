package consoleVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {

        int choice;

        Scanner input = new Scanner(System.in);

        System.out.println("Choose a Game Version\n1. Children\n2. Classic \n3. Expert\n4. Quit");
        choice = input.nextInt();

        switch (choice){
            case 1:
                kids();
                break;
            case 2:
                //classic();
                break;
            case 3:
                //expert();
            default:
                System.out.print("Quitting now, goodbye.....");
        }

        }

        public static void kids(){

            String name;

            Scanner input = new Scanner(System.in);

            int numGames = Games(), numGuesses = Guesses(), gamesPlayed=0, guessesMade = 0;
            char[] solution;

            boolean[] hints = {false, false, false, false};
            boolean equal;
            char[] guess = {'_', '_', '_', '_'};
            int count=0;

            System.out.println("Please enter your name: ");
            name = input.nextLine();

            while (numGames != -1) {

                solution = createCode();

                System.out.println(Arrays.toString(guess) + " " + Arrays.toString(hints));

                while (numGuesses != -1){

                    equal = false;

                    while(!equal){

                        System.out.println("Guess " + (guessesMade+1));
                        guess = createCode();


                        for(int i = 0; i < guess.length; i++){
                            if(compareCodeKids(guess, solution))        //resolve issue below
                                hints[i] = true;
                        }

                        System.out.println(Arrays.toString(guess) + " " + Arrays.toString(hints));

                        for (boolean hint : hints) {
                            if (hint)
                                count++;
                        }

                        if(count == 4) {
                            equal = true;
                            numGuesses = -1;
                            numGames = -1;
                        }

                    }
                    guessesMade++;
                }

                gamesPlayed++;
            }
            //if won
            System.out.print(name + " played " + gamesPlayed + " games " + " won in " + guessesMade + " guesses, Congratulations!");
        }

        /*public static void classic(){

            int numGames = Games(), numGuesses = Guesses(), gamesPlayed=0, guessesMade = 0;
            char[] solution = new char[4];

            int[] hints = {1, 2, 3};
            char[] guess = new char[4];

            while (gamesPlayed <= numGames) {

                solution = playerOneCode();

                while (guessesMade <= numGuesses){

                    guessesMade++;
                }

                //game starts

                gamesPlayed++;
            }

        }

        public static void expert(){

            int numGames = Games(), numGuesses = Guesses(), gamesPlayed=0, guessesMade = 0;
            char[] solution = new char[5];  //how to use validate code, need to add extra 'E' for empty

            int[] hints = {1, 2, 3};
            char[] guess = new char[4];

            while (gamesPlayed <= numGames) {

                solution = playerOneCode();

                while (guessesMade <= numGuesses){

                    guessesMade++;
                }

                //game starts

                gamesPlayed++;
            }

        }*/

        public static int Games(){

            int numGames = 0;

            Scanner input = new Scanner(System.in);
            //more validation - must be number
            while(numGames < 1 || numGames > 10){
                System.out.println("How many games would you like to play? (max 10) ");
                numGames = input.nextInt();
            }
            return numGames;
        }

        public static int Guesses(){

            int numGuesses=0;

            Scanner input = new Scanner(System.in);
            //more validation - must be number
            while(numGuesses < 1 || numGuesses > 12) {
                System.out.println("Please enter max number of guesses? (max 12) ");
                numGuesses = input.nextInt();
            }
            return numGuesses;
        }

        public static char[] createCode(){

            char colour;
            char[] s = new char[4];

            Scanner input = new Scanner(System.in);

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

        public static boolean validateColour(char c){

            char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
            ArrayList<Character> coloursList = new ArrayList<>();

            for (char colour : colours)
                coloursList.add(colour);

            for (Character ch : coloursList) {
                if (ch == c)
                    return true;
            }
            return false;
        }

    public static boolean compareCodeKids(char[] g, char[] s){
        //sort array and use search to improve efficiency

        //if multiple colours only one true hint (2,3 or 4)

        int count=0;

        for (char c : g) {
            for (char value : s)
                if (c == value)
                    count++;
        }
        return count == g.length;
    }
    }