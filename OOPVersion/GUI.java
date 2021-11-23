package OOPVersion;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class GUI {

    public static void main(String[] args) {

        //only save names if beat high score - top 3 (gold, silver, bronze)

        String player, playerOne = "", playerTwo = "", gameOption;
        boolean valid;
        int[] hints = {0, 0, 0, 0};
        int choice, i, numGames = numberValidator("games"), numGuesses = numberValidator("guesses"), gamesPlayed = 0, guessesMade = 0;
        char[] solution;
        char[] guess = {'_', '_', '_', '_'};

        //press start when ready  - - - load game GUI
        JOptionPane.showMessageDialog(null, "Press START When Ready");

        //Buttons
        gameOption = JOptionPane.showInputDialog(null, "1. START GAME\n2. VIEW LEADERBOARD\n3. QUIT", "Mastermind", JOptionPane.INFORMATION_MESSAGE);
        valid = false;

        while(!valid){

            if(gameOption.equals("1")){
                valid= true;
            }
            else if (gameOption.equals("2")){
                //display leaderboard
                valid= true;
            }
            else if (gameOption.equals("3")) {
                JOptionPane.showMessageDialog(null, "Quitting now, goodbye.....", "GAME OVER", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            else
                gameOption = JOptionPane.showInputDialog("INVALID!!1. START GAME\n2. VIEW LEADERBOARD\n3. QUIT");
        }


        //how many players (one or two) - create player object(s)
        //Buttons
        player = JOptionPane.showInputDialog("1. SINGLE PLAYER\n2. MULTIPLAYER\n3. QUIT");

        valid = false;

        while(!valid){

            if(player.equals("1")){

                playerOne = JOptionPane.showInputDialog("Please enter your name Player One: ");
                Player player1 = new Player(playerOne);
                Player player2 = new Player("Computer");
                valid= true;
            }
            else if (player.equals("2")){

                playerOne = JOptionPane.showInputDialog("Please enter your name Player One: ");
                Player player1 = new Player(playerOne);

                playerTwo =  JOptionPane.showInputDialog("Please enter your name Player Two: ");
                Player player2 = new Player(playerTwo);
                valid= true;
            }
            else if (player.equals("3")) {
                JOptionPane.showMessageDialog(null, "Quitting now, goodbye.....", "GAME OVER", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            else
                player = JOptionPane.showInputDialog("INVALID!!\n\n1. Single Player\n\n2. Multiplayer\n\n3. Quit");
        }

        //what game version (x3) - create game object
        String choiceAsString = JOptionPane.showInputDialog("Choose a Game Version\n1. Children\n2. Classic \n3. Expert\n4. QUIT");

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
                            VersionKids kidsOne = new VersionKids();

                            //PLAY GAME

                            while (numGames != gamesPlayed && numGames != -1) {

                                solution = kidsOne.createCode(playerOne);

                                JOptionPane.showMessageDialog(null, Arrays.toString(guess) + " " + Arrays.toString(hints),
                                        "START GAME", JOptionPane.INFORMATION_MESSAGE);


                                while (numGuesses != guessesMade && numGuesses != -1) {

                                    JOptionPane.showMessageDialog(null, "\nGuess" + (guessesMade+1), "PLAYER TWO", JOptionPane.INFORMATION_MESSAGE);

                                    guess = kidsOne.createCode(playerTwo);

                                    hints = kidsOne.compareCode(Arrays.copyOf(guess, guess.length), Arrays.copyOf(solution, solution.length));

                                    JOptionPane.showMessageDialog(null, Arrays.toString(guess) + " " + Arrays.toString(hints),
                                            "START GAME", JOptionPane.INFORMATION_MESSAGE);

                                    if(checkWin(hints)){
                                        numGuesses = -1;
                                        numGames = -1;
                                        break;
                                    }
                                    guessesMade++;
                                }
                                gamesPlayed++;
                            }
                            //if one
                            if(numGuesses == -1 && numGames == -1)
                                JOptionPane.showMessageDialog(null, playerTwo + " played " + gamesPlayed + " game(s) and" + " won in " + guessesMade + " guesses, Congratulations!",
                                        "YOU WIN!!!", JOptionPane.INFORMATION_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, playerTwo + " played " + gamesPlayed + " game(s) and lost. Better Look next time!",
                                        "GAME OVER", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 2:
                            VersionClassic classicOne = new VersionClassic();
                            break;
                        case 3:
                            VersionExpert expertOne = new VersionExpert();
                        default:
                            JOptionPane.showMessageDialog(null, "Quitting now, goodbye.....", "GAME OVER", JOptionPane.WARNING_MESSAGE);
                            System.exit(0);
                    }
                }
            } else {
                choiceAsString = JOptionPane.showInputDialog("INVALID!!\nChoose a Game Version\n1. Children\n2. Classic \n3. Expert\n4. QUIT");
            }
        }

        JOptionPane.showInputDialog("SELECT NUMBER OF GAMES", "1");

        JOptionPane.showInputDialog("SELECT NUMBER OF GUESSES", "5");






        System.exit(0);
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

    public static boolean checkWin(int[] h){

        int count = 0;

        for (int hint : h) {
            if (hint == 1)
                count++;
        }
        return count == 4;

    }
}
