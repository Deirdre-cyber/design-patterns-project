package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mastermind extends Game implements Serializable{

    private static int playButtonEventCount;
    private static int guessEventCount;
    private static final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);
    private static boolean guessButtonSet = false;
    private static boolean gameButtonSet = false;
    private static ArrayList<Color> pegColourList;


    public Mastermind(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
    }

    public static int getPlayButtonEventCount() {
        return playButtonEventCount;
    }
    public static void setPlayButtonEventCount(int playButtonEventCount) {
        Mastermind.playButtonEventCount = playButtonEventCount;
    }

    public static int getGuessEventCount() {
        return guessEventCount;
    }
    public static void setGuessEventCount(int guessEventCount) {
        Mastermind.guessEventCount = guessEventCount;
    }

    public static boolean isGuessButtonSet() {
        return guessButtonSet;
    }
    public static void setGuessButtonSet(boolean guessButtonSet) {
        Mastermind.guessButtonSet = guessButtonSet;
    }

    public static boolean isGameButtonSet() {
        return gameButtonSet;
    }
    public static void setGameButtonSet(boolean gameButtonSet) {
        Mastermind.gameButtonSet = gameButtonSet;
    }

    public static ArrayList<Color> getPegColourList() {
        return pegColourList;
    }
    public static void setPegColourList(ArrayList<Color> pegColourList) {
        Mastermind.pegColourList = pegColourList;
    }

    public static void main(String[] args) {

        //FIRST GUI - New game, Load game, View Leaderboard, Quit (keyboardListeners and mouseListeners)
        //GAME COLOURS
        pegColourList = new ArrayList<>();
        pegColourList.add(Color.WHITE);
        pegColourList.add(Color.YELLOW);
        pegColourList.add(Color.GREEN);
        pegColourList.add(Color.RED);
        pegColourList.add(Color.BLUE);
        pegColourList.add(Color.PINK);
        pegColourList.add(Color.CYAN);
        pegColourList.add(Color.ORANGE);

        JFrame game = new JFrame("MASTERMIND");

        JPanel gameOptionsPanel = new JPanel(new GridLayout(4, 1));
        game.add(gameOptionsPanel);

        //Create Buttons
        gameOptionsPanel.add(createStartButton());
        gameOptionsPanel.add(createLoadButton());
        gameOptionsPanel.add(createViewLeaderboardButton());
        gameOptionsPanel.add(createQuitButton());

        gameOptionsPanel.setFocusable(true);
        //TEST CODE
        gameOptionsPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S){

                    //TEST CODE
                    JFrame game = new JFrame("Mastermind");

                    JPanel mainBoard = new JPanel();
                    mainBoard.setLayout(new GridLayout(1,2));

                    mainBoard.add(createGameBoard());
                    //mainBoard.add(createPlayBoard());

                    game.add(mainBoard);

                    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    game.setLocation(550, 50);
                    game.setSize(500,600);
                    game.setResizable(false);
                    game.setVisible(true);
                }
                else if(e.getKeyCode() == KeyEvent.VK_L){
                    JOptionPane.showMessageDialog(null, "Load Saved game");
                    //Load last game state with serialisation - add save game button/key listener
                }
                else if(e.getKeyCode() == KeyEvent.VK_V){
                    JOptionPane.showMessageDialog(null, "Load Leaderboard");
                    //load
                }
                else if(e.getKeyCode() == KeyEvent.VK_Q){
                    int quit = JOptionPane.showConfirmDialog(null, "Are you sure you ant to quit?",
                            "QUIT", JOptionPane.YES_NO_CANCEL_OPTION);

                    if(quit == 1)
                        System.exit(0);
                }
            }
        });

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

    //CREATE LOAD BUTTON - Open Saved file : if doesn't exist 'Error'
    private static JButton createLoadButton(){

        JButton loadButton = new JButton("[L]OAD GAME");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        loadButton.setBackground(Color.GREEN);
        loadButton.setForeground(Color.BLACK);
        loadButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //open last game state - serialisation
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

        return loadButton;
    }


    //CREATE LEADERBOARD BUTTON - JOptionpane textarea with 'table' of top players
    private static JButton createViewLeaderboardButton(){

        JButton viewLeaderBoardButton = new JButton("[V]IEW LEADERBOARD");
        viewLeaderBoardButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        viewLeaderBoardButton.setBackground(Color.MAGENTA);
        viewLeaderBoardButton.setForeground(Color.BLACK);
        viewLeaderBoardButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        viewLeaderBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //open saved leaderboard files - serialisation
                //Leaderboard newLeaderboard = new Leaderboard(new Player[]{playerOne,playerTwo});

                File leaderboardFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/leaderboardfile.data");

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

    //CREATE QUIT BUTTON
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
        JPanel choicePanelLeft = new JPanel(new GridLayout(10, 1));
        choicePanelLeft.setBackground(Color.DARK_GRAY);

        //Create Right Panel
        JPanel choicePanelRight = new JPanel(new GridLayout(10,1));
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
        JTextField playerOneName = new JTextField();
        playerOneName.setBackground(Color.LIGHT_GRAY);
        playerOneName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        //Create Player Two Label
        JLabel playerTwoLabel = new JLabel("Enter Player Two:");
        playerTwoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerTwoLabel.setBackground(Color.BLUE);
        playerTwoLabel.setForeground(Color.WHITE);

        //Create Player Two Text Box
        JTextField playerTwoName = new JTextField("Computer");
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
        confirmPlayerButton.setForeground(Color.DARK_GRAY);
        confirmPlayerButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));

        //Confirm player options mouse listener
        confirmPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(!playerOneName.getText().equals("")){

                    Player playerOne = new Player(playerOneName.getText());
                    Player playerTwo = new Player(playerTwoName.getText());

                    setPlayer(new Player[]{playerOne, playerTwo});

                    //test code - REMOVE
                    System.out.print(playerOne + "" + playerTwo);

                    playButtonEventCount++;

                    //set button inactive??
                    confirmPlayerButton.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(null, "Please enter a name for Player One",
                            "Player One not Set", JOptionPane.ERROR_MESSAGE);
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
        guessAmountLabel.setBackground(Color.DARK_GRAY);

        //Create Guess Amount Text Box
        JTextField guessAmount = new JTextField();
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmount.setBackground(Color.LIGHT_GRAY);

        //Create Guess Amount Confirm Button
        JButton addGuessAmountButton = new JButton("Confirm Number of Games");
        addGuessAmountButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        addGuessAmountButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));
        addGuessAmountButton.setForeground(Color.DARK_GRAY);
        addGuessAmountButton.setBackground(Color.WHITE);

        addGuessAmountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!guessAmount.getText().equals("")){

                    int numGuesses = numberValidator(guessAmount.getText());

                    setNumberGuesses(numGuesses);

                    guessAmount.setText(String.format("%d", getNumberGuesses()));
                    guessButtonSet = true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Enter number of guesses (max 10)",
                            "No Number Entered", JOptionPane.ERROR_MESSAGE);
            }
        });


        //Create Game Amount Label
        JLabel gameAmountLabel = new JLabel("Enter amount of Games (max 10):");
        gameAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameAmountLabel.setForeground(Color.WHITE);

        //Create Game Amount Text Box
        JTextField gameAmount = new JTextField();
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmount.setBackground(Color.LIGHT_GRAY);

        //Create Game Amount Confirm Button
        JButton addGameAmountButton = new JButton("Confirm Number of Games");
        addGameAmountButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        addGameAmountButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.DARK_GRAY));
        addGameAmountButton.setForeground(Color.DARK_GRAY);
        addGameAmountButton.setBackground(Color.WHITE);

        addGameAmountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!gameAmount.getText().equals("")){

                    int numGames = numberValidator(gameAmount.getText());

                    setNumberGames(numGames);

                    gameAmount.setText(String.format("%d", getNumberGames()));
                    gameButtonSet = true;
                }
                else
                    JOptionPane.showMessageDialog(null, "Enter number of games (max 10)",
                            "No Number Entered", JOptionPane.ERROR_MESSAGE);
            }
        });


        //GAME VERSION RADIO BUTTON LISTENERS
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


        //Create "SET RULES" button
        JButton confirmButton = new JButton("SET RULES");
        confirmButton.setFont(buttonFont);
        confirmButton.setForeground(Color.DARK_GRAY);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));


        //Create 'START GAME' Button
        JButton playGame = new JButton("START GAME");
        playGame.setFont(buttonFont);
        playGame.setForeground(Color.DARK_GRAY);
        playGame.setBackground(Color.CYAN);
        playGame.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));
        playGame.setVisible(false);

        playGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //CREATE GAME BOARD GUI - REMADE EACH GAME : loop number of games

                //getPlayer()[0].toString() + getPlayer()[1].toString()

                if(getPlayer()[1].getPlayer().equals("Computer")) {
                    Color[] solutionCode = createCodeComputer();

                    //TEST CODE - DELETE
                    System.out.print(Arrays.toString(solutionCode));
                }
                else {
                    System.out.print("multiplayer");
                    //GUI TO CREATE CODE+++++++++++++++++++++++++++++++++
                }

                JFrame game = new JFrame("Mastermind");

                JPanel mainBoard = new JPanel();
                mainBoard.setLayout(new GridLayout(1,2));

                //mainBoard.add(createGameBoard());
                //mainBoard.add(createPlayBoard());

                game.add(mainBoard);

                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setLocation(550, 50);
                game.setSize(500,600);
                game.setResizable(false);
                game.setVisible(true);

                //once finished ask to save game++++++++++++++++++++
                //add to leaderboard++++++++++++++++++++++++++++++++
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (playButtonEventCount == 1) {

                    if(gameButtonSet && guessButtonSet){

                        if (kidsVersion.isSelected()) {
                            Game newGame = new Game(getPlayer(), getNumberGames(), getNumberGuesses(), "Kid's Version");

                            //test code - REMOVE
                            System.out.print(newGame);

                            confirmButton.setVisible(false);
                            playGame.setVisible(true);
                        }
                        else if (classicVersion.isSelected()) {

                            Game newGame = new Game(getPlayer(), getNumberGames(), getNumberGuesses(), "Classic Version");

                            //test code - REMOVE
                            System.out.print(newGame);

                            confirmButton.setVisible(false);
                            playGame.setVisible(true);

                        }
                        else if (kidsVersion.isSelected()) {

                            Game newGame = new Game(getPlayer(), getNumberGames(), getNumberGuesses(), "Expert Version");

                            //test code - REMOVE
                            System.out.print(newGame);

                            confirmButton.setVisible(false);
                            playGame.setVisible(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please select all Game Version",
                                    "Game Version Error", JOptionPane.ERROR_MESSAGE);

                            kidsVersion.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                            classicVersion.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                            expertVersion.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please confirm Game and guess amounts",
                                "Game and Guess Amounts Error", JOptionPane.ERROR_MESSAGE);

                        gameAmountLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                        guessAmountLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please confirm player(s)",
                            "Players Error", JOptionPane.ERROR_MESSAGE);

                    playerOneLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    playerTwoLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

                }
            }
        });

        //Add all components to Right Panel
        choicePanelRight.add(kidsVersion);
        choicePanelRight.add(classicVersion);
        choicePanelRight.add(expertVersion);
        choicePanelRight.add(gameAmountLabel);
        choicePanelRight.add(gameAmount);
        choicePanelRight.add(addGameAmountButton);
        choicePanelRight.add(guessAmountLabel);
        choicePanelRight.add(guessAmount);
        choicePanelRight.add(addGuessAmountButton);
        choicePanelRight.add(confirmButton);


        //Add Left Panel and Right Panel to Main Panel
        optionsPanel.add(choicePanelLeft);
        optionsPanel.add(playGame);
        optionsPanel.add(choicePanelRight);

        //Add main Panel to Frame
        choiceFrame.add(optionsPanel);

        choiceFrame.setLocation(550, 50);
        choiceFrame.setSize(700,400);
        choiceFrame.setResizable(false);
        choiceFrame.setVisible(true);

    }
    public static int numberValidator(String s) {

        int num = 0;
        boolean valid;
        int i;

        valid = false;

        while (!valid) {
            for (i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    break;
                }
            }
            if (i == s.length() && !s.equals("")) {
                num = Integer.parseInt(s);

                if (num >= 1 && num <= 10) {
                    valid = true;
                }
                else {
                s = JOptionPane.showInputDialog(null,"Invalid! Please enter a valid number");
                }
            }
        }
        return num;
    }

    //CREATE GAME BOARD++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //CONTINUE FROM HERE
    //create board and finish game

   private static JPanel createGameBoard(){

        //CREATE GAME BOARD PANEL
       JPanel gameBoard = new JPanel(new GridLayout(1, 2));

       //Create LEFT PANEL - GUESSES
       JPanel panelLeft = new JPanel(new GridLayout(12, 1));
       panelLeft.setBackground(Color.CYAN);

       //SOLUTION PANEL - BLANK DURING GAMEPLAY
       JPanel solutionPanel = new JPanel();     //layout manager - contains 4 buttons

       //ADD SOLUTION CODE WHEN GAME OVER
       solutionPanel.add(new JLabel("MASTERMIND"));
       solutionPanel.setBackground(Color.RED);

       //ADD SOLUTION PANEL TO LEFT PANEL
       panelLeft.add(solutionPanel);

        //METHOD TO CREATE GUESS PANELS - depends on getNumberGuesses() - CHANGE NUMBERS, TESTING
       //REFRESH EACH ROUND
       for(int i = 0; i < 6; i++){
           panelLeft.add(createGuessPanels());
       }

       //rest blank 'placeholder' - CHANGE NUMBERS, TESTING
       for(int i = 0; i < 10-6; i++){
           panelLeft.add(new JPanel());
       }

        //Create RIGHT PANEL - HINT
       JPanel panelRight = new JPanel(new GridLayout(12, 1));
       panelRight.setBackground(Color.BLUE);

       gameBoard.add(panelLeft);
       gameBoard.add(panelRight);

        return gameBoard;
    }

    /*private static JPanel createPlayBoard(){


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




        //add hidden button code in expert level
        /*JButton blankColour = new JButton("[blank]");
        blankColour.setBackground(Color.GRAY);
        blankColour.setForeground(Color.WHITE);
        colourButtonPanel.add(blankColour);*/

        /*playPanel.add(colourButtonPanel);

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
                    //guessPanels[1].add(guessPanel);
                    //increment guess counter etc
                }
                else
                    JOptionPane.showMessageDialog(null, "You have not completed your guess",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        playPanel.add(makeGuess);

        return playPanel;
    }*/

    public static Color[] createCodeComputer() {

        int num;
        Color[] s = new Color[4];

        for (int i = 0; i < 4; i++) {
            num = (int)(Math.random()*7);

            s[i] = getPegColourList().get(num);
        }
        return s;
    }

    public static JPanel createGuessPanels(){

        JPanel guessPanel = new JPanel(new GridLayout(1,4));
        JButton[] guessColours = new JButton[4];

        for(int i = 0; i < guessColours.length; i++){
            guessColours[i] = new JButton(" ");
            guessColours[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
            guessPanel.add(guessColours[i]);
        }

        return guessPanel;
    }

}