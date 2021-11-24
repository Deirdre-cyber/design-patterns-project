package consoleVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {

        String choiceAsString, playerOne, playerTwo;
        boolean valid;
        int i, choice;

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your name Player One: ");
        playerOne = input.nextLine();

        System.out.println("Please enter your name Player Two: ");
        playerTwo = input.nextLine();

        System.out.println("Choose a Game Version\n1. Children\n2. Classic \n3. Expert\n4. Quit");
        choiceAsString = input.nextLine();

        valid = false;

        while (!valid) {
            for (i = 0; i < choiceAsString.length(); i++) {
                if (!Character.isDigit(choiceAsString.charAt(i))) {
                    break;
                }
            }
            if (i == choiceAsString.length() && !choiceAsString.equals("")) {
                choice = Integer.parseInt(choiceAsString);

                if (choice >= 1 && choice <= 4) {
                    valid = true;

                    switch (choice) {
                        case 1:
                            kids(playerOne, playerTwo);
                            break;
                        case 2:
                            classic(playerOne, playerTwo);
                            break;
                        case 3:
                            expert(playerOne, playerTwo);
                        default:
                            System.out.print("Quitting now, goodbye.....");
                    }
                }
            } else {
                System.out.println("Invalid! Please choose a Game Version\n1. Children\n2. Classic \n3. Expert\n4. Quit");
                choiceAsString = input.nextLine();
            }
        }

    }

    public static void kids(String playerOne, String playerTwo) { //class?

        int numGames = numberValidator("games"), numGuesses = numberValidator("guesses"), gamesPlayed = 0, guessesMade = 0;
        char[] solution;
        boolean[] hints = {false, false, false, false};
        char[] guess = {'_', '_', '_', '_'};

        while (numGames != gamesPlayed && numGames != -1) {

            solution = createCode(playerOne);

            System.out.println(Arrays.toString(guess) + " " + Arrays.toString(hints));


            while (numGuesses != guessesMade && numGuesses != -1) {

                    System.out.println("\nGuess " + (guessesMade+1));

                    guess = createCode(playerTwo);

                    ++guessesMade;

                    hints = compareCodeKids(Arrays.copyOf(guess, guess.length), Arrays.copyOf(solution, solution.length));

                    System.out.println(Arrays.toString(guess) + Arrays.toString(hints));

                    if(checkWin(hints)){
                        numGuesses = -1;
                        numGames = -1;
                        break;
                    }
            }
            gamesPlayed++;
        }
            if(numGuesses == -1 && numGames == -1)
                System.out.println(playerTwo + " played " + gamesPlayed + " game(s) and" + " won in " + guessesMade + " guess(es), Congratulations!");
            else
                System.out.println("Game over");
    }

    public static void classic(String playerOne, String playerTwo){

        int numGames = numberValidator("games"), numGuesses = numberValidator("guesses"), gamesPlayed=0, guessesMade = 0;
        char[] solution;

        int[] hints = {0, 0, 0, 0};
        char[] guess = new char[4];

        while (numGames != gamesPlayed && numGames != -1) {

            solution = createCode(playerOne);

            System.out.println(Arrays.toString(guess) + " " + Arrays.toString(hints));


            while (numGuesses != guessesMade && numGuesses != -1) {

                    System.out.println("\nGuess " + (guessesMade+1));

                    guess = createCode(playerTwo);

                    ++guessesMade;

                    hints = compareCodeClassic(Arrays.copyOf(guess, guess.length), Arrays.copyOf(solution, solution.length));

                    System.out.println(Arrays.toString(guess) + Arrays.toString(hints));

                    if(checkWinClassic(hints)){
                        numGuesses = -1;
                        numGames = -1;
                        break;
                    }
            }
            gamesPlayed++;
        }
            if(numGuesses == -1 && numGames == -1)
                System.out.println(playerTwo + " played " + gamesPlayed + " game(s) and" + " won in " + guessesMade + " guess(es), Congratulations!");
            else
                System.out.println("Game over");

       }

        public static void expert(String playerOne, String playerTwo){

            int numGames = numberValidator("games"), numGuesses = numberValidator("guesses"), gamesPlayed=0, guessesMade = 0;
            char[] solution;

            int[] hints = {0, 0, 0, 0};
            char[] guess = new char[4];

            while (numGames != gamesPlayed && numGames != -1) {

                solution = createCodeExpert(playerOne);

                System.out.println(Arrays.toString(guess) + " " + Arrays.toString(hints));

                while (numGuesses != guessesMade && numGuesses != -1) {

                    System.out.println("\nGuess " + (guessesMade+1));

                    guess = createCodeExpert(playerTwo);

                    ++guessesMade;

                    hints = compareCodeClassic(Arrays.copyOf(guess, guess.length), Arrays.copyOf(solution, solution.length));

                    System.out.println(Arrays.toString(guess) + Arrays.toString(hints));

                    if(checkWinClassic(hints)){
                        numGuesses = -1;
                        numGames = -1;
                        break;
                    }
                }
                gamesPlayed++;
            }
            if(numGuesses == -1 && numGames == -1)
                System.out.println(playerTwo + " played " + gamesPlayed + " game(s) and" + " won in " + guessesMade + " guess(es), Congratulations!");
            else
                System.out.println("Game over");

        }

    public static int numberValidator(String s) {

        String numAsString;
        int num = 0;
        boolean valid;
        int i;

        Scanner input = new Scanner(System.in);

        System.out.println("How many " + s + " would you like to play? (max 10) ");
        numAsString = input.nextLine();

        valid = false;
        while (!valid) {
            for (i = 0; i < numAsString.length(); i++) {
                if (!Character.isDigit(numAsString.charAt(i))) {
                    break;
                }
            }
            if (i == numAsString.length() && !numAsString.equals("")) {
                num = Integer.parseInt(numAsString);

                if (num >= 1 && num <= 10) {
                    valid = true;
                }
            } else {
                System.out.println("Invalid! How many " + s + " would you like to play? (max 10) ");
                numAsString = input.nextLine();
            }
        }

        return num;
    }

    public static char[] createCode(String play) {

        char colour;
        char[] s = new char[4];

        Scanner input = new Scanner(System.in);

        System.out.println("Your turn " + play);

        for (int i = 0; i < 4; i++) {

            System.out.println("Please enter colour " + (i + 1) + " ");
            colour = input.next().charAt(0);

            while (!validateColour(colour) || !Character.isLetter(colour)) {
                System.out.println("Invalid colour. Please enter a valid colour for colour " + (i + 1) + " ");
                colour = input.next().charAt(0);
            }
            s[i] = colour;
        }
        return s;
    }

    public static char[] createCodeExpert(String play) {

        char colour;
        char[] s = new char[4];

        Scanner input = new Scanner(System.in);

        System.out.println("Your turn " + play);

        for (int i = 0; i < 4; i++) {

            System.out.println("Please enter colour " + (i + 1) + " ");
            colour = input.next().charAt(0);

            while (!validateColourExpert(colour) || !Character.isLetter(colour)) {
                System.out.println("Invalid colour. Please enter a valid colour for colour " + (i + 1) + " ");
                colour = input.next().charAt(0);
            }
            s[i] = colour;
        }
        return s;
    }

    public static boolean validateColour(char c) {

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

    public static boolean validateColourExpert(char c) {

        char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v', 'K'};
        ArrayList<Character> coloursList = new ArrayList<>();

        for (char colour : colours)
            coloursList.add(colour);

        for (Character ch : coloursList) {
            if (ch == c)
                return true;
        }
        return false;
    }

    public static boolean[] compareCodeKids(char[] g, char[] s) {

        boolean[] h = new boolean[4];
        int x, y;

        for (x = 0; x < g.length; x++) {
            for (y = 0; y < s.length; y++) {
                if (g[x] == s[y]) {
                    h[x] = true;
                    s[y] = 'X';
                    break;
                }
            }
        }
        return h;
    }
    public static int[] compareCodeClassic(char[] g, char[] s){ //reuse for expert with added extra colour

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
    public static boolean checkWin(boolean[] h){

        int count = 0;

        for (boolean hint : h) {
            if (hint)
                count++;
        }
        return count == 4;

    }

    public static boolean checkWinClassic(int[] h){

        int count = 0;

        for (int hint : h) {
            if (hint == 1)
                count++;
        }
        return count == 4;
    }
}