package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mastermind extends Game implements Serializable{

    private static int playButtonEventCount;
    private static int guessButtonEventCount;
    private static final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);
    private static boolean guessButtonSet = false;
    private static boolean gameButtonSet = false;
    private static ArrayList<Color> pegColourList;
    private static JFrame newGame;

    public Mastermind(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
    }

    public static int getPlayButtonEventCount() {
        return playButtonEventCount;
    }
    public void setPlayButtonEventCount(int playButtonEventCount) {
        Mastermind.playButtonEventCount = playButtonEventCount;
    }

    public static int getGuessButtonEventCount() {
        return guessButtonEventCount;
    }
    public static void setGuessButtonEventCount(int guessButtonEventCount) {
        Mastermind.guessButtonEventCount = guessButtonEventCount;
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
                    mainBoard.add(createPlayBoard());

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



    //----------------START OPTIONS CREATE BUTTONS-----------------
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

    //CREATE LOAD BUTTON - Open Saved file : If doesn't exist 'Error'
    private static JButton createLoadButton(){

        JButton loadButton = new JButton("[L]OAD GAME");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        loadButton.setBackground(Color.GREEN);
        loadButton.setForeground(Color.BLACK);
        loadButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                File savedGameFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/savedGameFile.data");

                try {
                    FileInputStream inputStream = new FileInputStream(savedGameFile);

                    ObjectInputStream gameInputStream = new ObjectInputStream(inputStream);

                    newGame = (JFrame) gameInputStream.readObject();

                    newGame.setVisible(true);

                    gameInputStream.close();

                } catch (FileNotFoundException ex) {

                    JOptionPane.showMessageDialog(null, "No Files Found",
                            "File Not Found", JOptionPane.ERROR_MESSAGE);

                } catch (IOException ioException) {

                    JOptionPane.showMessageDialog(null, "Error with file",
                            "File Error", JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException classNotFoundException) {

                    JOptionPane.showMessageDialog(null, "Class not found",
                            "File Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        return loadButton;
    }

    //CREATE LEADERBOARD BUTTON - JOptionPane textarea with 'table' of top players
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



    //--------------PLAYER & GAME OPTIONS---------------------
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
        JRadioButton kidsVersion = new JRadioButton("KIDS");
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
                    setGuessButtonSet(true);
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
                    setGameButtonSet(true);
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
                playGAME();

            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (getPlayButtonEventCount() == 1) {

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

    //CREATE GAME BOARD
   private static JPanel createGameBoard(){

        //CREATE GAME BOARD PANEL
       JPanel gameBoard = new JPanel(new GridLayout(1, 2));

       //Create LEFT PANEL - GUESSES
       JPanel panelLeft = new JPanel(new GridLayout(12, 1));
       panelLeft.setBackground(Color.CYAN);

       //SOLUTION PANEL - BLANK DURING GAMEPLAY
       JPanel solutionPanel = new JPanel();     //layout manager - contains 4 buttons

       //ADD SOLUTION CODE WHEN GAME OVER
       solutionPanel.add(new JLabel(getNumberGuesses() + " attempts left!"));
       solutionPanel.setBackground(Color.CYAN);

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
       panelRight.setBackground(Color.CYAN);

       //PLACEHOLDER LABEL
       panelRight.add(new JLabel());

       //METHOD TO CREATE HINT PANELS
       for(int i = 0; i < 6; i++){
           panelRight.add(createHintsPanels());
       }
       //rest blank 'placeholder' - CHANGE NUMBERS, TESTING
       for(int i = 0; i < 10-6; i++){
           panelRight.add(new JPanel());
       }

       gameBoard.add(panelLeft);
       gameBoard.add(panelRight);

        return gameBoard;
    }

    //CREATE PLAYING BOARD
    private static JPanel createPlayBoard(){

        //CREATE PLAY BOARD PANEL
        JPanel playPanel = new JPanel(new GridLayout(5, 1));
        playPanel.setBackground(Color.GRAY);


        //CREATE SAVE BUTTON
        JButton saveButton = new JButton("SAVE GAME");
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.GRAY));
        playPanel.add(saveButton);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //SAVE GAME STATE
                File saveGameFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/savedGameFile.data");

                try {
                    FileOutputStream outStream = new FileOutputStream(saveGameFile);

                    ObjectOutputStream gameOutputStream = new ObjectOutputStream(outStream);

                    gameOutputStream.writeObject(newGame);

                    outStream.close();

                    JOptionPane.showMessageDialog(null, "Successfully saved game",
                            "Saved Game", JOptionPane.INFORMATION_MESSAGE);

                } catch (FileNotFoundException ex) {

                    JOptionPane.showMessageDialog(null, "Could not find file",
                            "File Not Found", JOptionPane.ERROR_MESSAGE);

                } catch (IOException ioException) {

                    JOptionPane.showMessageDialog(null, "Error saving file",
                            "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //CREATE GUESS PANEL
        JPanel guessPanel = new JPanel(new GridBagLayout());
        guessPanel.setBackground(Color.GRAY);
        JButton[] guessButtons = new JButton[4];

        for(int i = 0; i < guessButtons.length; i++){

            guessButtons[i] = new JButton("  ");
            guessButtons[i].setBackground(Color.LIGHT_GRAY);

            guessButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                        ((JButton)(e.getSource())).setBackground(Color.LIGHT_GRAY);
                        guessButtonEventCount--;
                    }
                }
            });
            guessPanel.add(guessButtons[i]);
        }


        //CREATE COLOUR PANEL - Chosen colours add to guess panel
        JPanel colourButtonPanel = new JPanel(new GridLayout(4, 2));
        colourButtonPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));

        //CREATE COLOUR BUTTONS
        JButton[] colourButtons = new JButton[8];

        for(int i = 0; i < getPegColourList().size(); i++){
            colourButtons[i] = new JButton();
            colourButtons[i].setBackground(getPegColourList().get(i));
            colourButtons[i].setForeground(getPegColourList().get(i));
            colourButtons[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.GRAY));
            colourButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    if(getGuessButtonEventCount() < guessButtons.length) {
                        Color color = ((JButton)e.getSource()).getBackground();
                        guessButtons[getGuessButtonEventCount()].setBackground(color);
                    }
                    else {
                        System.out.print(getGuessButtonEventCount());
                        setGuessButtonEventCount(0);
                    }
                    guessButtonEventCount++;
                }
            });
            colourButtonPanel.add(colourButtons[i]);
        }

        //ADD COLOUR BUTTON PANEL TO MAIN PANEL
        playPanel.add(colourButtonPanel);

        //ADD BLANK PLACEHOLDER
        playPanel.add(new JLabel());

        //ADD GUESS BUTTON PANEL TO MAIN PANEL
        playPanel.add(guessPanel);

        //ADD BUTTON TO ADD GUESS TO GAME PANEL
        JButton makeGuess = new JButton("Make Guess");
        makeGuess.setBackground(Color.WHITE);
        makeGuess.setFont(GAME_FONT);
        makeGuess.setBackground(Color.GRAY);
        makeGuess.setForeground(Color.WHITE);
        makeGuess.setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.WHITE));

        makeGuess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(guessButtonEventCount == 4){      //guess event must be correct
                    JOptionPane.showMessageDialog(null, "Guess made");

                    //add colours to left panel
                    //add guessPanel to first panel on left

                    setNumberGuesses(getNumberGuesses()-1);
                }
                else
                    JOptionPane.showMessageDialog(null, "You have not completed your guess",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        playPanel.add(makeGuess);

        return playPanel;
    }



    //CREATE GUESS PANEL
    public static JPanel createGuessPanels(){

        JPanel guessPanel = new JPanel(new GridLayout(1,4));
        JButton[] playGuessButtons = new JButton[4];

        for(int i = 0; i < playGuessButtons.length; i++){
            playGuessButtons[i] = new JButton(" ");
            playGuessButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            playGuessButtons[i].setBackground(Color.WHITE);
            guessPanel.add(playGuessButtons[i]);
        }

        return guessPanel;
    }

    //CREATE HINTS PANEL
    public static JPanel createHintsPanels(){

        JPanel hintsPanel = new JPanel(new GridLayout(1,4));
        JButton[] hintButtons = new JButton[4];

        for(int i = 0; i < hintButtons.length; i++){
            hintButtons[i] = new JButton(" ");
            hintButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
            hintButtons[i].setBackground(Color.WHITE);
            hintsPanel.add(hintButtons[i]);
        }

        return hintsPanel;
    }


    public static void playGAME(){

        String codeMaker = getPlayer()[1].getPlayer(), codeBreaker = getPlayer()[0].getPlayer();

        //for loop = numGames
        for(int x = 0; x < getNumberGames(); x++){

            //swap players+++++++++++++++++++++++++++++++++++++++
            if(x % 2 == 0){
                codeMaker = getPlayer()[0].getPlayer();
                codeBreaker = getPlayer()[1].getPlayer();
            }
            else {
                codeMaker = getPlayer()[1].getPlayer();
                codeBreaker = getPlayer()[0].getPlayer();
            }

            if(getPlayer()[1].getPlayer().equals("Computer")) {

                if(Game.getVersion().equals("KIDS")){

                    Color[] solutionCode = createCodeComputer();

                    JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());

                    newGame = gameGUI();

                    //TEST CODE - DELETE++++++++++++++++++++++++++++++++++++++
                    System.out.print(Arrays.toString(solutionCode));

                }
                else if (Game.getVersion().equals("CLASSIC")){

                    Color[] solutionCode = createCodeComputer();

                    JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());

                    newGame = gameGUI();

                    //TEST CODE - DELETE++++++++++++++++++++++++++++++++++++++
                    System.out.print(Arrays.toString(solutionCode));
                }
                else{

                    Color[] solutionCode = createCodeComputer();

                    JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());

                    newGame = gameGUI();

                    //TEST CODE - DELETE++++++++++++++++++++++++++++++++++++++
                    System.out.print(Arrays.toString(solutionCode));
                }

            }
            else {
                if(Game.getVersion().equals("KIDS")){

                    System.out.print("multiplayer");

                    //CREATE CODE GUI
                    JFrame createCode = new JFrame("Please select colours " + codeMaker);

                    JPanel code = new JPanel(new GridLayout(3,1));

                    //bottom code panel
                    JPanel codePanel = new JPanel(new GridBagLayout());
                    codePanel.setBackground(Color.GRAY);

                    JButton[] codeColorButtons = new JButton[4];

                    for(int i = 0; i < codeColorButtons.length; i++){

                        codeColorButtons[i] = new JButton("  ");
                        codeColorButtons[i].setBackground(Color.LIGHT_GRAY);

                        codeColorButtons[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                                    ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                                }
                            }
                        });
                        codePanel.add(codeColorButtons[i]);
                    }

                    //top color panel
                    JPanel colourPicker = new JPanel(new GridLayout(2,4));

                    JButton[] colourPickerButton = new JButton[8];

                    for(int i = 0; i < getPegColourList().size(); i++) {
                        colourPickerButton[i] = new JButton();
                        colourPickerButton[i].setBackground(getPegColourList().get(i));
                        colourPickerButton[i].setForeground(getPegColourList().get(i));
                        colourPickerButton[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

                        colourPickerButton[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {

                                //error here
                                for(int j = 0; j < codeColorButtons.length; j++){
                                    Color color = ((JButton)e.getSource()).getBackground();
                                    codeColorButtons[j].setBackground(color);
                                }
                            }
                        });
                        colourPicker.add(colourPickerButton[i]);
                    }
                    code.add(colourPicker);

                    JButton setCode = new JButton("Set code");

                    code.add(colourPicker);
                    code.add(codePanel);
                    code.add(setCode);

                    createCode.add(code);

                    setCode.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(codeColorButtons[0].getBackground() == Color.LIGHT_GRAY){
                                JOptionPane.showMessageDialog(null, "Player " + getPlayer()[1].getPlayer() + " must choose code");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());
                                newGame = gameGUI();
                            }
                        }
                    });

                    createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    createCode.setLocation(550, 50);
                    createCode.setSize(500,600);
                    createCode.setResizable(false);
                    createCode.setVisible(true);
                }
                else if(Game.getVersion().equals("CLASSIC")){
                    System.out.print("multiplayer");

                    //GUI TO CREATE CODE+++++++++++++++++++++++++++++++++
                    JFrame createCode = new JFrame("Please select colours " + codeMaker);

                    JPanel code = new JPanel(new GridLayout(3,1));

                    //bottom code panel
                    JPanel codePanel = new JPanel(new GridBagLayout());
                    codePanel.setBackground(Color.GRAY);

                    JButton[] codeColorButtons = new JButton[4];

                    for(int i = 0; i < codeColorButtons.length; i++){

                        codeColorButtons[i] = new JButton("  ");
                        codeColorButtons[i].setBackground(Color.LIGHT_GRAY);

                        codeColorButtons[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                                    ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                                }
                            }
                        });
                        codePanel.add(codeColorButtons[i]);
                    }

                    //top color panel
                    JPanel colourPicker = new JPanel(new GridLayout(2,4));

                    JButton[] colourPickerButton = new JButton[8];

                    for(int i = 0; i < getPegColourList().size(); i++) {
                        colourPickerButton[i] = new JButton();
                        colourPickerButton[i].setBackground(getPegColourList().get(i));
                        colourPickerButton[i].setForeground(getPegColourList().get(i));
                        colourPickerButton[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

                        colourPickerButton[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {

                                //error here
                                for(int j = 0; j < codeColorButtons.length; j++){
                                    Color color = ((JButton)e.getSource()).getBackground();
                                    codeColorButtons[j].setBackground(color);
                                }
                            }
                        });
                        colourPicker.add(colourPickerButton[i]);
                    }
                    code.add(colourPicker);

                    JButton setCode = new JButton("Set code");

                    code.add(colourPicker);
                    code.add(codePanel);
                    code.add(setCode);

                    createCode.add(code);

                    setCode.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(codeColorButtons[0].getBackground() == Color.LIGHT_GRAY){
                                JOptionPane.showMessageDialog(null, "Player " + getPlayer()[1].getPlayer() + " must choose code");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());
                                newGame = gameGUI();
                            }
                        }
                    });

                    createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    createCode.setLocation(550, 50);
                    createCode.setSize(500,600);
                    createCode.setResizable(false);
                    createCode.setVisible(true);
                }
                else{
                    System.out.print("multiplayer");

                    //GUI TO CREATE CODE+++++++++++++++++++++++++++++++++
                    JFrame createCode = new JFrame("Please select colours " + codeMaker);

                    JPanel code = new JPanel(new GridLayout(3,1));

                    //bottom code panel
                    JPanel codePanel = new JPanel(new GridBagLayout());
                    codePanel.setBackground(Color.GRAY);

                    JButton[] codeColorButtons = new JButton[4];

                    for(int i = 0; i < codeColorButtons.length; i++){

                        codeColorButtons[i] = new JButton("  ");
                        codeColorButtons[i].setBackground(Color.LIGHT_GRAY);

                        codeColorButtons[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                                    ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                                }
                            }
                        });
                        codePanel.add(codeColorButtons[i]);
                    }

                    //top color panel
                    JPanel colourPicker = new JPanel(new GridLayout(2,4));

                    JButton[] colourPickerButton = new JButton[8];

                    for(int i = 0; i < getPegColourList().size(); i++) {
                        colourPickerButton[i] = new JButton();
                        colourPickerButton[i].setBackground(getPegColourList().get(i));
                        colourPickerButton[i].setForeground(getPegColourList().get(i));
                        colourPickerButton[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

                        colourPickerButton[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {

                                //error here
                                for(int j = 0; j < codeColorButtons.length; j++){
                                    Color color = ((JButton)e.getSource()).getBackground();
                                    codeColorButtons[j].setBackground(color);
                                }
                            }
                        });
                        colourPicker.add(colourPickerButton[i]);
                    }
                    code.add(colourPicker);

                    JButton setCode = new JButton("Set code");

                    code.add(colourPicker);
                    code.add(codePanel);
                    code.add(setCode);

                    createCode.add(code);

                    setCode.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(codeColorButtons[0].getBackground() == Color.LIGHT_GRAY){
                                JOptionPane.showMessageDialog(null, "Player " + getPlayer()[1].getPlayer() + " must choose code");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + getPlayer()[0].getPlayer() + " and " + getPlayer()[1].getPlayer());
                                newGame = gameGUI();
                            }
                        }
                    });

                    createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    createCode.setLocation(550, 50);
                    createCode.setSize(500,600);
                    createCode.setResizable(false);
                    createCode.setVisible(true);
                }

            }
        }
    }


    public static JFrame gameGUI(){

        JFrame game = new JFrame("Mastermind");

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

        return game;
    }


    //CREATE CODE FOR COMPUTER - EDIT DEPENDING ON VERSION
    public static Color[] createCodeComputer() {

        int num;
        Color[] s = new Color[4];

        for (int i = 0; i < s.length; i++) {
            num = (int)(Math.random()*7);

            s[i] = getPegColourList().get(num);
        }
        return s;
    }

    public static Color[] createCodeHuman(){

        JFrame createCode = new JFrame("Please select colours " + getPlayer()[1].getPlayer());

        JPanel code = new JPanel(new GridLayout(3,1));

        //bottom code panel
        JPanel codePanel = new JPanel(new GridBagLayout());
        codePanel.setBackground(Color.GRAY);

        JButton[] codeColorButtons = new JButton[4];

        for(int i = 0; i < codeColorButtons.length; i++){

            codeColorButtons[i] = new JButton("  ");
            codeColorButtons[i].setBackground(Color.LIGHT_GRAY);

            codeColorButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(((JButton)(e.getSource())).getBackground() != Color.LIGHT_GRAY) {
                        ((JButton) (e.getSource())).setBackground(Color.LIGHT_GRAY);
                    }
                }
            });
            codePanel.add(codeColorButtons[i]);
        }

        //top color panel
        JPanel colourPicker = new JPanel(new GridLayout(2,4));

        JButton[] colourPickerButton = new JButton[8];

        for(int i = 0; i < getPegColourList().size(); i++) {
            colourPickerButton[i] = new JButton();
            colourPickerButton[i].setBackground(getPegColourList().get(i));
            colourPickerButton[i].setForeground(getPegColourList().get(i));
            colourPickerButton[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

            colourPickerButton[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    //error here
                    for(int j = 0; j < codeColorButtons.length; j++){
                        Color color = ((JButton)e.getSource()).getBackground();
                        codeColorButtons[j].setBackground(color);
                    }
                }
            });
            colourPicker.add(colourPickerButton[i]);
        }
        code.add(colourPicker);


        });

        return code;

    }


    //VALIDATION METHODS
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

}