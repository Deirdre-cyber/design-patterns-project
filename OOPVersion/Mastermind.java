package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class Mastermind extends Game implements Serializable{

    private static int playButtonEventCount;
    private static Player playerOne;
    private static Player playerTwo;

    public Mastermind(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
    }



    //New Game - Open options GUI
    //Options GUI - single/multi : enter name of player(s) : Button to set player
    // kids/classic/expert : enter number of guesses and games:  Button to set choices

    //Once all validated open GAMEBOARD - details needed [name(s), numguesses, numgames]


    //Load game - Open Saved file : if doesn't exist 'Error'

    //View Leaderboard - JOptionpane textarea with 'table' of top players

    //Quit - Confirm and quit

    public static void main(String[] args) {

        //FIRST GUI - New game, Load game, View Leaderboard, Quit (keyboardListeners and mouseListeners)

        JFrame game = new JFrame("MASTERMIND");

        JPanel gameOptionsPanel = new JPanel(new GridLayout(4, 1));
        game.add(gameOptionsPanel);

        //Create Buttons
        gameOptionsPanel.add(createStartButton());
        gameOptionsPanel.add(createLoadButton());
        gameOptionsPanel.add(createViewLeaderboardButton());
        gameOptionsPanel.add(createQuitButton());

        gameOptionsPanel.setFocusable(true);

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocation(600, 50);
        game.setSize(400, 400);
        game.setResizable(false);
        game.setVisible(true);
    }

    //----------------USER DEFINED METHODS : START OPTIONS CREATE BUTTONS----------------------------

    private static JButton createStartButton(){

        JButton startButton = new JButton("[S]TART NEW GAME");
        startButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        startButton.setBackground(Color.ORANGE);
        startButton.setForeground(Color.BLACK);
        startButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseGameOptions();
            }
        });

        return startButton;
    }

    private static JButton createLoadButton(){

        JButton loadButton = new JButton("[L]OAD GAME");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        loadButton.setBackground(Color.GREEN);
        loadButton.setForeground(Color.BLACK);
        loadButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Load Saved game");
                //open last game state - serialisation
                File loadGame = new File("C:/Users/College Girl/IdeaProjects/miniproject/savefile.data");

            }
        });

        return loadButton;
    }

    private static JButton createViewLeaderboardButton(){

        JButton viewLeaderBoardButton = new JButton("[V]IEW LEADERBOARD");
        viewLeaderBoardButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        viewLeaderBoardButton.setBackground(Color.MAGENTA);
        viewLeaderBoardButton.setForeground(Color.BLACK);
        viewLeaderBoardButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        viewLeaderBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Load Leaderboard");
                //open saves files - serialisation

                File leaderboardFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/savefile.data");

                try {
                    FileInputStream inStream = new FileInputStream(leaderboardFile);

                    int fileSize = (int)leaderboardFile.length();
                    byte[] leaderboardArray = new byte[fileSize];

                    inStream.read(leaderboardArray);

                    //String and add to textArea and display

                    inStream.close();

                } catch (FileNotFoundException ex) {

                    JOptionPane.showMessageDialog(null, "No Files Found",
                            "File Not Found", JOptionPane.ERROR_MESSAGE);

                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error with file",
                            "File Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        return viewLeaderBoardButton;
    }

    private static JButton createQuitButton(){

        JButton quitButton = new JButton("[Q]UIT");
        quitButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        quitButton.setBackground(Color.BLUE);
        quitButton.setForeground(Color.BLACK);
        quitButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int quit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
                        "QUIT", JOptionPane.YES_NO_OPTION);

                if(quit == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        return quitButton;
    }

    //----------PLAYER & GAME OPTIONS---------------------

    private static void chooseGameOptions(){

        //Create Frame
        JFrame choiceFrame = new JFrame();

        //Create Panel
        JPanel optionsPanel = new JPanel(new GridLayout(1,3));
        optionsPanel.setBackground(Color.DARK_GRAY);

        //Create font for buttons
        Font buttonFont = new Font("Monospaced", Font.PLAIN, 18);

        //Create Left Panel
        JPanel choicePanelLeft = new JPanel(new GridLayout(8, 1));
        choicePanelLeft.setBackground(Color.DARK_GRAY);

        //Create Right Panel
        JPanel choicePanelRight = new JPanel(new GridLayout(8,1));
        choicePanelRight.setBackground(Color.DARK_GRAY);


        //-----PLAYER OPTIONS - LEFT PANEL--------------------------

        //Create single player radio button
        JRadioButton singlePlayerButton = new JRadioButton("SINGLE PLAYER");
        singlePlayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        singlePlayerButton.setBackground(Color.DARK_GRAY);
        singlePlayerButton.setForeground(Color.WHITE);
        singlePlayerButton.setSelected(true);

        //Create multi player radio button
        JRadioButton multiPayerButton = new JRadioButton("MULTIPLAYER");
        multiPayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        multiPayerButton.setBackground(Color.DARK_GRAY);
        multiPayerButton.setForeground(Color.WHITE);

        //Create Player One Label
        JLabel playerOneLabel = new JLabel("Enter Player One:");
        playerOneLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerOneLabel.setBackground(Color.BLUE);
        playerOneLabel.setForeground(Color.WHITE);

        //Create Player One Text Box
        JTextField playerOneName = new JTextField();//must be complete to continue if multi play
        playerOneName.setBackground(Color.LIGHT_GRAY);
        playerOneName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        //Create Player Two Label
        JLabel playerTwoLabel = new JLabel("Enter Player Two:");
        playerTwoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerTwoLabel.setBackground(Color.BLUE);
        playerTwoLabel.setForeground(Color.WHITE);

        //Create Player Two Text Box
        JTextField playerTwoName = new JTextField("Computer");//must be complete to continue
        playerTwoName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        playerTwoName.setBackground(Color.GRAY);
        playerTwoName.setEditable(false);

        //Single Player item listener
        singlePlayerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    multiPayerButton.setSelected(false);
                    playerTwoName.setBackground(Color.GRAY);
                    playerTwoName.setEditable(false);
                    playerTwoName.setText("Computer");
                    playerOneName.requestFocus();
                }
            }
        });

        //Multi Player item listener
        multiPayerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    singlePlayerButton.setSelected(false);
                    playerTwoName.setBackground(Color.LIGHT_GRAY);
                    playerTwoName.setEditable(true);
                    playerTwoName.setText(null);
                    playerOneName.requestFocus();

                }
            }
        });

        //Create Confirm player options
        JButton confirmPlayerButton = new JButton("ADD PLAYER");
        confirmPlayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        confirmPlayerButton.setBackground(Color.WHITE);
        confirmPlayerButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));

        //Confirm player options mouse listener
        confirmPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //validation - radio buttons are set correctly
                playerOne = new Player(playerOneName.getText());
                playerTwo = new Player(playerTwoName.getText());



                System.out.print(playerOne + "" + playerTwo);

                playButtonEventCount++;
                //set button inactive??
                confirmPlayerButton.setVisible(false);
            }
        });


        //add all components to Left Panel
        choicePanelLeft.add(singlePlayerButton);
        choicePanelLeft.add(multiPayerButton);
        choicePanelLeft.add(Box.createHorizontalStrut(10));  //only need size if use other layout manager
        choicePanelLeft.add(playerOneLabel);
        choicePanelLeft.add(playerOneName);
        choicePanelLeft.add(playerTwoLabel);
        choicePanelLeft.add(playerTwoName);
        choicePanelLeft.add(confirmPlayerButton);

        //-----GAME VERSION OPTIONS - RIGHT PANEL---------------------

        //Create kids version radio button
        JRadioButton kidsVersion = new JRadioButton("EASY");
        kidsVersion.setFont(buttonFont);
        kidsVersion.setBackground(Color.DARK_GRAY);
        kidsVersion.setForeground(Color.WHITE);

        //Create classic version radio button
        JRadioButton classicVersion = new JRadioButton("CLASSIC");
        classicVersion.setFont(buttonFont);
        classicVersion.setBackground(Color.DARK_GRAY);
        classicVersion.setForeground(Color.WHITE);

        //Create expert version radio button
        JRadioButton expertVersion = new JRadioButton("EXPERT");
        expertVersion.setFont(buttonFont);
        expertVersion.setBackground(Color.DARK_GRAY);
        expertVersion.setForeground(Color.WHITE);

        //Create Guess Amount Label
        JLabel guessAmountLabel = new JLabel("Enter amount of Guesses (max 10):");
        guessAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        guessAmountLabel.setForeground(Color.WHITE);

        //Create Guess Amount Text Box
        JTextField guessAmount = new JTextField();
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmount.setBackground(Color.LIGHT_GRAY);


        //Create Game Amount Label
        JLabel gameAmountLabel = new JLabel("Enter amount of Games (max 10):");
        gameAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameAmountLabel.setForeground(Color.WHITE);

        //Create Game Amount Text Box
        JTextField gameAmount = new JTextField();//must be complete to continue
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmount.setBackground(Color.LIGHT_GRAY);


        kidsVersion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    classicVersion.setSelected(false);
                    expertVersion.setSelected(false);
                    gameAmount.requestFocus();
                }
            }
        });

        classicVersion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    kidsVersion.setSelected(false);
                    expertVersion.setSelected(false);
                    gameAmount.requestFocus();
                }
            }
        });

        expertVersion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    classicVersion.setSelected(false);
                    kidsVersion.setSelected(false);
                    gameAmount.requestFocus();
                }
            }
        });


        //Create "Confirm" button
        JButton confirmButton = new JButton("SET GAME");
        confirmButton.setFont(buttonFont);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //improve this....
                //validation : players must be set, validate numbers

                int numGames = numberValidator(gameAmount.getText()), numGuesses = numberValidator(guessAmount.getText());

                if(kidsVersion.isSelected() && playButtonEventCount == 1){
                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Kid's Version");
                    System.out.print(newGame);
                }
                else if(classicVersion.isSelected() && playButtonEventCount == 1){
                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Classic Version");
                    System.out.print(newGame);
                }
                else if(kidsVersion.isSelected() && playButtonEventCount == 1){
                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Expert Version");
                    System.out.print(newGame);
                }
                else
                    JOptionPane.showMessageDialog(null, "Please select Game Version and/or Set PLayer(s)",
                            "Game Options Not Completed", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Add all components to Right Panel
        choicePanelRight.add(kidsVersion);
        choicePanelRight.add(classicVersion);
        choicePanelRight.add(expertVersion);
        choicePanelRight.add(gameAmountLabel);
        choicePanelRight.add(gameAmount);
        choicePanelRight.add(guessAmountLabel);
        choicePanelRight.add(guessAmount);
        choicePanelRight.add(confirmButton);

        //Add Left Panel and Right Panel to Main Panel
        optionsPanel.add(choicePanelLeft);
        optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(choicePanelRight);

        //Add main Panel to Frame
        choiceFrame.add(optionsPanel);

        choiceFrame.setLocation(550, 50);
        choiceFrame.setSize(700,400);
        choiceFrame.setResizable(false);
        choiceFrame.setVisible(true);

    }
    public static int numberValidator(String s) {

        String choice;
        int num = 0;
        boolean valid;
        int i;

        choice = JOptionPane.showInputDialog(null,"Please enter a valid number");

        valid = false;
        while (!valid) {
            for (i = 0; i < choice.length(); i++) {
                if (!Character.isDigit(choice.charAt(i))) {
                    break;
                }
            }
            if (i == choice.length() && !choice.equals("")) {
                num = Integer.parseInt(choice);

                if (num >= 1 && num <= 10) {
                    valid = true;
                }
            } else {
                JOptionPane.showMessageDialog(null,"Invalid! Please enter a valid number");
            }
        }
        return num;
    }

}


