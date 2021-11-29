package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameBoardGUI extends Mastermind{

    //CREATE GAME BOARD GUI - REMADE EACH GAME
    JFrame game = new JFrame("Mastermind");

    private JButton[] guessButtons;
    private JPanel[] guessPanels;
    private int guessEventCount = 0;
    private final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);

    public GameBoardGUI(Game newGame){

        JPanel mainBoard = new JPanel();
        mainBoard.setLayout(new GridLayout(1,2));

        mainBoard.add(createGameBoard());
        mainBoard.add(createPlayBoard());

        game.add(mainBoard);

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocation(550, 50);
        game.setSize(500,600);
        game.setResizable(false);
        game.setVisible(true);
    }

    //GETTERS AND SETTERS...

    private JPanel createGameBoard(){

        JPanel gameBoard = new JPanel(new GridLayout(1, 2));

        //RIGHT PANEL
        JPanel panelLeft = new JPanel(new GridLayout(12, 1));
        panelLeft.setBackground(Color.GRAY);

        //----------TO BE SHOWN WHILE PLAYER ONE SELECTING (MULTIPLAYER)-----------

        /*JPanel solutionPanel = new JPanel();

        //BETTER WAY TO CHECK PLAYERS......
        String players="";

        for(int i = 0; i < getPlayer().length; i++){
            players += getPlayer()[i] + "";
            System.out.print(players);
        }
        char[] computerSolution;

        if(players.contains("Computer")){
            computerSolution = createCodeComputer();
        }

        JButton[] solutionButtons = new JButton[4];     //set by createCode() in Player

        for(int j = 0; j < solutionButtons.length; j++) {

            solutionButtons[j] = new JButton("  ");
            solutionButtons[j].setBackground(Color.LIGHT_GRAY);

            solutionButtons[j].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (((JButton) (e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                        ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                        guessEventCount--; //make sure this works
                        //also make sure can't delete once guess button pressed
                    }
                }
            });
            solutionPanel.add(solutionButtons[j]);
        }
        panelLeft.add(solutionPanel);*/

        //TO BE SHOWN DURING GAME PLAY
        JLabel solutionLabelLeft = new JLabel("MASTER");
        solutionLabelLeft.setFont(GAME_FONT);
        solutionLabelLeft.setForeground(Color.WHITE);
        solutionLabelLeft.setHorizontalAlignment(SwingConstants.RIGHT);

        panelLeft.add(solutionLabelLeft);

        JPanel[] guessPanels = new JPanel[10]; //Number is numGuesses chosen

        //MAKE ARRAY OF BUTTONS IN EACH PANEL
        for(int i = 0; i < guessPanels.length; i++){
            guessPanels[i] = new JPanel(new FlowLayout());
            guessPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            guessPanels[i].setBackground(Color.WHITE);
            panelLeft.add(guessPanels[i]);
        }

        JLabel gameCounterLabel = new JLabel("Game No" + getNumberGames()); //after game play "You win/you lose"
        gameCounterLabel.setFont(GAME_FONT);
        gameCounterLabel.setForeground(Color.WHITE);
        gameCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelLeft.add(gameCounterLabel);

        //RIGHT PANEL : HINTS PANEL
        JPanel panelRight = new JPanel(new GridLayout(12, 1));
        panelRight.setBackground(Color.GRAY);

        JLabel solutionLabelRight = new JLabel("MIND");
        solutionLabelRight.setFont(GAME_FONT);
        solutionLabelRight.setForeground(Color.WHITE);
        solutionLabelRight.setHorizontalAlignment(SwingConstants.LEFT);

        panelRight.add(solutionLabelRight);

        JPanel[] hintPanels = new JPanel[10];

        //MAKE ARRAY OF BUTTONS IN PANEL
        for(int i = 0; i < hintPanels.length; i++){
            hintPanels[i] = new JPanel(new FlowLayout());
            hintPanels[i].add(new JLabel("O O O O"));       //make array of buttons
            hintPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panelRight.add(hintPanels[i]);
        }

        JLabel hintsLabel = new JLabel("hints"); //set by numGames

        hintsLabel.setFont(GAME_FONT);
        hintsLabel.setForeground(Color.WHITE);
        hintsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelRight.add(hintsLabel);

        gameBoard.add(panelLeft);
        gameBoard.add(panelRight);

        return gameBoard;
    }
    private JPanel createPlayBoard(){

        JPanel playPanel = new JPanel(new GridLayout(5, 1));
        playPanel.setBackground(Color.GRAY);

        JButton saveButton = new JButton("SAVE GAME");
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.GRAY));
        playPanel.add(saveButton);

        JPanel colourButtonPanel = new JPanel(new GridLayout(4, 2));
        colourButtonPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));

        JButton[] colourButtons = new JButton[8];

        ArrayList<Color> buttonColourList = new ArrayList<>();
        buttonColourList.add(Color.WHITE);
        buttonColourList.add(Color.YELLOW);
        buttonColourList.add(Color.GREEN);
        buttonColourList.add(Color.RED);
        buttonColourList.add(Color.BLUE);
        buttonColourList.add(Color.PINK);
        buttonColourList.add(Color.CYAN);
        buttonColourList.add(Color.ORANGE);

        for(int i = 0; i < buttonColourList.size(); i++){
            colourButtons[i] = new JButton();
            colourButtons[i].setBackground(buttonColourList.get(i));
            colourButtons[i].setForeground(buttonColourList.get(i));
            colourButtons[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.GRAY));
            colourButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    if(guessEventCount < guessButtons.length)
                        guessButtons[guessEventCount].setBackground(((JButton)e.getSource()).getBackground());
                    else {
                        System.out.print(guessEventCount);
                        guessEventCount = 0;
                    }
                    guessEventCount++;
                }
            });
            colourButtonPanel.add(colourButtons[i]);
        }

        /*
        //add hidden button code in expert level
        blankColour = new JButton("[blank]");
        blankColour.setBackground(Color.GRAY);
        blankColour.setForeground(Color.WHITE);
        colourButtonPanel.add(blankColour);*/

        playPanel.add(colourButtonPanel);

        JLabel placeholder2 = new JLabel("  ");
        playPanel.add(placeholder2);

        JPanel guessPanel = new JPanel(new GridBagLayout());
        guessPanel.setBackground(Color.GRAY);
        JButton[] guessButtons = new JButton[4];

        for(int i = 0; i < guessButtons.length; i++){
            //add graphics - 4 circle lines...new class or method : paintComponent
            guessButtons[i] = new JButton("  ");
            guessButtons[i].setBackground(Color.LIGHT_GRAY);
            guessButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                        ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                        guessEventCount--; //make sure this works
                        //also make sure can't delete once guess button pressed
                    }
                }
            });
            guessPanel.add(guessButtons[i]);
        }

        playPanel.add(guessPanel);

        JButton makeGuess = new JButton("Make Guess");
        makeGuess.setBackground(Color.WHITE);
        makeGuess.setFont(GAME_FONT);
        makeGuess.setBackground(Color.GRAY);
        makeGuess.setForeground(Color.WHITE);
        makeGuess.setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.WHITE));
        makeGuess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(guessEventCount == 4){      //guess event must be correct
                    JOptionPane.showMessageDialog(null, "Guess made");
                    guessPanels[1].add(guessPanel);
                    //increment guess counter etc
                }
                else
                    JOptionPane.showMessageDialog(null, "You have not completed your guess",
                                                "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        playPanel.add(makeGuess);

        return playPanel;
    }

    public static char[] createCodeComputer() {

        int num;
        char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        char[] s = new char[4];

        for (int i = 0; i < 4; i++) {
            num = (int)(Math.random()*7);

            s[i] = colours[num];
        }
        return s;
    }

    //code for human player in multiplayer
    public static char[] createCode(String play) {

        char colour;
        String colourAsString;
        char[] s = new char[4];


        for (int i = 0; i < 4; i++) {

           colourAsString = JOptionPane.showInputDialog("Please enter colour " + (i + 1) + " ");
           colour = colourAsString.charAt(0);

            while (!validateColour(colour) || !Character.isLetter(colour)) {
                colourAsString = JOptionPane.showInputDialog("Please enter colour " + (i + 1) + " ");
                colour = colourAsString.charAt(0);
            }
            s[i] = colour;
        }
        return s;
    }

    public static boolean validateColour(char c) {

        char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};
        ArrayList<Character> coloursList = new ArrayList<>();

        //if game is expert colours.add('K')

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
