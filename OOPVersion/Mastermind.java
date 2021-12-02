package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that defines a game of Mastermind
 * @author Deirdre Lee
 * */
public class Mastermind extends Game implements Serializable{

    private static int playButtonEventCount;
    private static int guessButtonEventCount;
    private static final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);
    private static boolean guessButtonSet = false;
    private static boolean gameButtonSet = false;
    private static ArrayList<Color> pegColourList;
    private static JFrame newGame;
    private static int chooseButtonEventCount;
    private static Color[] codeHuman;
    private static Color[] solutionCode;


    /**
     * Mastermind 4 argument constructo. Calls 4 mutators from the super class Game
     * @param players the two game players
     * @param numberGames the number of games set by the human player
     * @param numberGuesses the number of guesses set by the human player
     * @param version the game version chosen by the human player
     * */
    public Mastermind(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
    }

    /**
     * method to count how many times the set player button was pressed to ensure that the players names were set
     * @return int value specifying count
     * */
    public static int getPlayButtonEventCount() {
        return playButtonEventCount;
    }

    /**
     * method to count how many times the Guess button was pressed to ensure game can be played
     * @return int value specifying count
     * */
    public static int getGuessButtonEventCount() {
        return guessButtonEventCount;
    }

    /**
     * method to set how many times the Guess button was pressed to ensure game can be played
     * @param guessButtonEventCount the number times the guess button is pressed
     * */
    public static void setGuessButtonEventCount(int guessButtonEventCount) {
        Mastermind.guessButtonEventCount = guessButtonEventCount;
    }

    /**
     * method to set the Guess Button to true (pressed) or false (not pressed)
     * @param guessButtonSet whether the Guess Button has been set or not
     * */
    public static void setGuessButtonSet(boolean guessButtonSet) {
        Mastermind.guessButtonSet = guessButtonSet;
    }

    /**
     * method to set the Game Button to true (pressed) or false (not pressed)
     * @param gameButtonSet whether the Game Button has been set or not
     * */
    public static void setGameButtonSet(boolean gameButtonSet) {
        Mastermind.gameButtonSet = gameButtonSet;
    }

    /**
     * method to get the colours that cane be used in the game
     * @return Array list of colours that can be used in the game
     * */
    public static ArrayList<Color> getPegColourList() {
        return pegColourList;
    }

    /**
     * The main method where the pegColourList is initialised. This is also where the first GUI is created using
     * four methods (createStartButton(), createLoadButton(), createViewLeaderboardButton(), createQuitButton())
     * There are also Key Listeners connected to the four buttons
     * */
    public static void main(String[] args) {

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

        gameOptionsPanel.add(createStartButton());
        gameOptionsPanel.add(createLoadButton());
        gameOptionsPanel.add(createViewLeaderboardButton());
        gameOptionsPanel.add(createQuitButton());

        gameOptionsPanel.setFocusable(true);

        gameOptionsPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S){
                    chooseGameOptions();
                }
                else if(e.getKeyCode() == KeyEvent.VK_L){
                    JOptionPane.showMessageDialog(null, "Load Saved game");

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
                else if(e.getKeyCode() == KeyEvent.VK_V){
                    JOptionPane.showMessageDialog(null, "Load Leaderboard");

                    File leaderboardFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/leaderboard.data");

                    try {
                        FileInputStream inputStream = new FileInputStream(leaderboardFile);

                        ObjectInputStream leaderOutputStream = new ObjectInputStream(inputStream);

                        Leaderboard leaderboard = (Leaderboard) leaderOutputStream.readObject();

                        JOptionPane.showMessageDialog(null, leaderboard, "LEADERBOARD", JOptionPane.INFORMATION_MESSAGE);

                        leaderOutputStream.close();

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

    /**
     * The method to create the 'START GAME' button.
     * @return A JButton that has a mouse listener connected which calls the chooseGameOptions() method
     * */
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

    /**
     * The method to create the 'LOAD GAME' button.
     * @return A JButton that has a mouse listener connected which calls the File method to load the last saved game.
     * @throws FileNotFoundException if no file found
     * @throws IOException if there is an error
     * @throws ClassNotFoundException if there is no class associated
     * */
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

    /**
     * The method to create the 'VIEW LEADERBOARD' button.
     * @return A JButton that has a mouse listener connected which calls the File method to load the leaderboard.
     * @throws FileNotFoundException if no file found
     * @throws IOException if there is an error
     * @throws ClassNotFoundException if there is no class associated
     * */
    private static JButton createViewLeaderboardButton(){

        JButton viewLeaderBoardButton = new JButton("[V]IEW LEADERBOARD");
        viewLeaderBoardButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        viewLeaderBoardButton.setBackground(Color.MAGENTA);
        viewLeaderBoardButton.setForeground(Color.BLACK);
        viewLeaderBoardButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        viewLeaderBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                File leaderboardFile = new File("C:/Users/College Girl/IdeaProjects/miniproject/leaderboard.data");

                try {
                    FileInputStream inputStream = new FileInputStream(leaderboardFile);

                    ObjectInputStream leaderOutputStream = new ObjectInputStream(inputStream);

                    Leaderboard leaderboard = (Leaderboard) leaderOutputStream.readObject();

                    JOptionPane.showMessageDialog(null, leaderboard, "LEADERBOARD", JOptionPane.INFORMATION_MESSAGE);

                    leaderOutputStream.close();

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

        return viewLeaderBoardButton;
    }

    /**
     * The method to create the 'QUIT' button.
     * @return A JButton that has a mouse listener connected which exits the game if confirmed by the user.
     * */
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


    /**
     * The method to create the Player and Game options GUI. Here the amount of players, player names, game type (kids,
     * classic or expert), max number of guesses and max number of games are set. There are itemListeners connected to each radio button
     * to determine game type and player amount.
     * There is a button to confirm player amount, a button to confirm Number of Games,a button to confirm Number of Guesses
     * and finally a button to confirm all the choices made
     * */
    private static void chooseGameOptions(){

        JFrame choiceFrame = new JFrame();

        JPanel optionsPanel = new JPanel(new GridLayout(1,3));
        optionsPanel.setBackground(Color.DARK_GRAY);

        Font buttonFont = new Font("Monospaced", Font.PLAIN, 18);

        JPanel choicePanelLeft = new JPanel(new GridLayout(10, 1));
        choicePanelLeft.setBackground(Color.DARK_GRAY);

        JPanel choicePanelRight = new JPanel(new GridLayout(10,1));
        choicePanelRight.setBackground(Color.DARK_GRAY);


        JRadioButton singlePlayerButton = new JRadioButton("SINGLE PLAYER");
        singlePlayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        singlePlayerButton.setBackground(Color.DARK_GRAY);
        singlePlayerButton.setForeground(Color.WHITE);
        singlePlayerButton.setSelected(true);


        JRadioButton multiPayerButton = new JRadioButton("MULTIPLAYER");
        multiPayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        multiPayerButton.setBackground(Color.DARK_GRAY);
        multiPayerButton.setForeground(Color.WHITE);


        JLabel playerOneLabel = new JLabel("Enter Player One:");
        playerOneLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerOneLabel.setBackground(Color.BLUE);
        playerOneLabel.setForeground(Color.WHITE);

        JTextField playerOneName = new JTextField();
        playerOneName.setBackground(Color.LIGHT_GRAY);
        playerOneName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));


        JLabel playerTwoLabel = new JLabel("Enter Player Two:");
        playerTwoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerTwoLabel.setBackground(Color.BLUE);
        playerTwoLabel.setForeground(Color.WHITE);

        JTextField playerTwoName = new JTextField("Computer");
        playerTwoName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        playerTwoName.setBackground(Color.GRAY);
        playerTwoName.setEditable(false);

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


        JButton confirmPlayerButton = new JButton("ADD PLAYER");
        confirmPlayerButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        confirmPlayerButton.setBackground(Color.WHITE);
        confirmPlayerButton.setForeground(Color.DARK_GRAY);
        confirmPlayerButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));


        confirmPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(!playerOneName.getText().equals("")){

                    Player playerOne = new Player(playerOneName.getText(), 0);
                    Player playerTwo = new Player(playerTwoName.getText(), 0);

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


        choicePanelLeft.add(singlePlayerButton);
        choicePanelLeft.add(multiPayerButton);
        choicePanelLeft.add(Box.createHorizontalStrut(10));  //only need size if use other layout manager
        choicePanelLeft.add(playerOneLabel);
        choicePanelLeft.add(playerOneName);
        choicePanelLeft.add(playerTwoLabel);
        choicePanelLeft.add(playerTwoName);
        choicePanelLeft.add(confirmPlayerButton);


        JRadioButton kidsVersion = new JRadioButton("KIDS");
        kidsVersion.setFont(buttonFont);
        kidsVersion.setBackground(Color.DARK_GRAY);
        kidsVersion.setForeground(Color.WHITE);

        JRadioButton classicVersion = new JRadioButton("CLASSIC");
        classicVersion.setFont(buttonFont);
        classicVersion.setBackground(Color.DARK_GRAY);
        classicVersion.setForeground(Color.WHITE);

        JRadioButton expertVersion = new JRadioButton("EXPERT");
        expertVersion.setFont(buttonFont);
        expertVersion.setBackground(Color.DARK_GRAY);
        expertVersion.setForeground(Color.WHITE);



        JLabel guessAmountLabel = new JLabel("Enter amount of Guesses (max 10):");
        guessAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        guessAmountLabel.setForeground(Color.WHITE);
        guessAmountLabel.setBackground(Color.DARK_GRAY);


        JTextField guessAmount = new JTextField();
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmount.setBackground(Color.LIGHT_GRAY);


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



        JLabel gameAmountLabel = new JLabel("Enter amount of Games (max 10):");
        gameAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameAmountLabel.setForeground(Color.WHITE);


        JTextField gameAmount = new JTextField();
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmount.setBackground(Color.LIGHT_GRAY);


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


        JButton confirmButton = new JButton("SET RULES");
        confirmButton.setFont(buttonFont);
        confirmButton.setForeground(Color.DARK_GRAY);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));


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
                            confirmButton.setVisible(false);
                            playGame.setVisible(true);
                        }
                        else if (classicVersion.isSelected()) {

                            Game newGame = new Game(getPlayer(), getNumberGames(), getNumberGuesses(), "Classic Version");
                            confirmButton.setVisible(false);
                            playGame.setVisible(true);

                        }
                        else if (kidsVersion.isSelected()) {

                            Game newGame = new Game(getPlayer(), getNumberGames(), getNumberGuesses(), "Expert Version");
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


        optionsPanel.add(choicePanelLeft);
        optionsPanel.add(playGame);
        optionsPanel.add(choicePanelRight);

        choiceFrame.add(optionsPanel);

        choiceFrame.setLocation(550, 50);
        choiceFrame.setSize(700,400);
        choiceFrame.setResizable(false);
        choiceFrame.setVisible(true);

    }

    /**
     * The method to create the left side of the game board where the guess and hints buttons are located.
     * There is also a solution panel where the solution will be displayed at the end of the game or when the game is won.
     * @return A JPanel that makes the left side of the game board
     * */
   private static JPanel createGameBoard(){

       int maxGuesses = 10;

       JPanel gameBoard = new JPanel(new GridLayout(1, 2));

       JPanel panelLeft = new JPanel(new GridLayout(12, 1));
       panelLeft.setBackground(Color.CYAN);

       JPanel solutionPanel = new JPanel();

       solutionPanel.add(new JLabel(getNumberGuesses() + " attempts left!"));
       solutionPanel.setBackground(Color.CYAN);

       panelLeft.add(solutionPanel);


       for(int i = 0; i < getNumberGuesses(); i++){
           panelLeft.add(createGuessPanels());
       }

       for(int i = 0; i < maxGuesses-getNumberGuesses(); i++){
           panelLeft.add(new JPanel());
       }

       JPanel panelRight = new JPanel(new GridLayout(12, 1));
       panelRight.setBackground(Color.CYAN);

       panelRight.add(new JLabel());

       for(int i = 0; i < getNumberGuesses(); i++){
           panelRight.add(createHintsPanels());
       }

       for(int i = 0; i < maxGuesses-getNumberGuesses(); i++){
           panelRight.add(new JPanel());
       }

       gameBoard.add(panelLeft);
       gameBoard.add(panelRight);

        return gameBoard;
    }

    /**
     * The method to create the right side of the game board where the game colour options, guess panel and submit guess button are located.
     * There is also a save button with a mouseListener to save the current state of the game.
     * @return A JPanel that makes the right side of the game board
     * */
    private static JPanel createPlayBoard(){

        JPanel playPanel = new JPanel(new GridLayout(5, 1));
        playPanel.setBackground(Color.GRAY);

        JButton saveButton = new JButton("SAVE GAME");
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.GRAY));
        playPanel.add(saveButton);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

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

        JPanel guessPanel = new JPanel(new GridBagLayout());
        guessPanel.setBackground(Color.GRAY);
        JButton[] guessButtons = new JButton[4];
        Color[] guessColors = new Color[4];

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


        JPanel colourButtonPanel = new JPanel(new GridLayout(4, 2));
        colourButtonPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));

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
                        guessColors[getGuessButtonEventCount()] = color;
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

        playPanel.add(colourButtonPanel);

        playPanel.add(new JLabel());

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

                String codeMaker = getPlayer()[1].getPlayer(), codeBreaker = getPlayer()[0].getPlayer();

                if(getNumberGuesses() != 0){

                    if(guessButtonEventCount == 4){
                        JOptionPane.showMessageDialog(null, "Guess made");

                        if(codeBreaker.equals("Computer")) {
                            Color[] hints = compareCode(guessColors, solutionCode);
                            JOptionPane.showMessageDialog(null, hints);
                        }
                        else {
                            Color[] hints = compareCode(guessColors, codeHuman);
                            JOptionPane.showMessageDialog(null, hints);
                        }

                        setNumberGuesses(getNumberGuesses()-1);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "You have not completed your guess",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No more guesses left", "GAME OVER", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        playPanel.add(makeGuess);

        return playPanel;
    }

    /**
     * The method to create the guess panels on the left side of the board game. This method will be called after each game to 'reset' the guess buttons
     * @return A JPanel that has four grey buttons
     * */
    private static JPanel createGuessPanels(){

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

    /**
     * The method to create the hint panels on the left side of the board game. This method will be called after each game to 'reset' the hint buttons
     * @return A JPanel that has four grey buttons
     * */
    private static JPanel createHintsPanels(){

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

    /**
     * The method where the game is 'Code Maker' adn 'Code Breaker' are set. The game direction is set here, dependent on the factors chose in the
     * chooseGameOptions() method
     * */
    private static void playGAME(){

        String codeMaker, codeBreaker;

        //loop games - use checker to confirm - game over decrement numGames

        //SWAP PLAYERS
        if(getNumberGames() % 2 == 0){
                codeMaker = getPlayer()[0].getPlayer();
                codeBreaker = getPlayer()[1].getPlayer();
            }
        else {
                codeMaker = getPlayer()[1].getPlayer();
                codeBreaker = getPlayer()[0].getPlayer();
            }

        if(codeMaker.equals("Computer")) {

            if(Game.getVersion().equals("KIDS")){

                solutionCode = createCodeComputer();

                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + codeBreaker + " and " + codeMaker);

                newGame = gameGUI();


            }
            else if (Game.getVersion().equals("CLASSIC")){

                solutionCode = createCodeComputer();

                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + codeBreaker + " and " + codeMaker);

                newGame = gameGUI();
            }
            else{

                getPegColourList().add(Color.CYAN);

                solutionCode = createCodeComputer();

                JOptionPane.showMessageDialog(null, "Welcome to the " + Game.getVersion() + " game " + codeBreaker + " and " + codeMaker);

                newGame = gameGUI();
            }

        }
        else {
            if(Game.getVersion().equals("KIDS")){

                //CREATE CODE GUI
                JFrame createCode = new JFrame("Please select colours " + codeMaker);

                createCode.add(createCodePicker());

                Color[] hints = compareCode(codeHuman, codeHuman);

                if(checkWin(hints)){
                    JOptionPane.showMessageDialog(null, "Congratulations, you win!", "WINNER", JOptionPane.INFORMATION_MESSAGE);
                    //number of wins++
                }

                createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                createCode.setLocation(550, 50);
                createCode.setSize(500,600);
                createCode.setResizable(false);
                createCode.setVisible(true);

            }
            else if(Game.getVersion().equals("CLASSIC")){

                    //GUI TO CREATE CODE
                JFrame createCode = new JFrame("Please select colours " + codeMaker);

                createCode.add(createCodePicker());

                Color[] hints = compareCode(codeHuman, codeHuman);

                if(checkWin(hints)){
                    JOptionPane.showMessageDialog(null, "Congratulations, you win!", "WINNER", JOptionPane.INFORMATION_MESSAGE);
                    //number of wins++
                }

                createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                createCode.setLocation(550, 50);
                createCode.setSize(500,600);
                createCode.setResizable(false);
                createCode.setVisible(true);
            }
            else{
                //GUI TO CREATE CODE
                JFrame createCode = new JFrame("Please select colours " + codeMaker);

                createCode.add(createCodePicker());

                Color[] hints = compareCode(codeHuman, codeHuman);

                if(checkWin(hints)){
                    JOptionPane.showMessageDialog(null, "Congratulations, you win!", "WINNER", JOptionPane.INFORMATION_MESSAGE);
                    //number of wins++
                }

                createCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                createCode.setLocation(550, 50);
                createCode.setSize(500,600);
                createCode.setResizable(false);
                createCode.setVisible(true);
            }

        }
    }

    /**
     * The method where the game is 'Code Maker' adn 'Code Breaker' are set. The game direction is set here, dependent on the factors chose in the
     * chooseGameOptions() method
     * */
    private static JFrame gameGUI(){

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

    /**
     * The method where 'code' is created ny the CPU Player
     * @return Color array that represents the solution created by the CPU Player
     * */
    private static Color[] createCodeComputer() {

        int num;
        Color[] s = new Color[4];

        for (int i = 0; i < s.length; i++) {
            num = (int)(Math.random()*7);

            s[i] = getPegColourList().get(num);
        }
        System.out.print(Arrays.toString(s));
        return s;
    }

    /**
     * The method where the 'code' is created by the Human Player
     * @return Color array that represents the solution created by the Human Player
     * */
    private static Color[] createCodeHuman(JButton[] codeColorButtons){

        Color[] codeColours = new Color[4];

        for(int i = 0; i < codeColorButtons.length; i++){
            for(int j = 0; j < getPegColourList().size(); j++){
                if(codeColorButtons[i].getBackground().equals(getPegColourList().get(i))){
                    codeColours[i] = getPegColourList().get(i);
                }
            }
        }
        return codeColours;
    }

    /**
     * The method that creates the GUI where the Human Player can choose the colours they want to set as the 'code'
     * @return JPanel that has the colours available to choose and a button to set those colours as the solution
     * */
    private static JPanel createCodePicker(){

        JPanel code = new JPanel(new GridLayout(3,1));

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
                        chooseButtonEventCount--;
                    }
                }
            });
            codePanel.add(codeColorButtons[i]);
        }

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

                    if(chooseButtonEventCount < codeColorButtons.length) {
                        Color color = ((JButton)e.getSource()).getBackground();
                        codeColorButtons[chooseButtonEventCount].setBackground(color);
                    }
                    else {
                        System.out.print(getGuessButtonEventCount());
                        setGuessButtonEventCount(0);
                    }
                    chooseButtonEventCount++;
                }
            });
            colourPicker.add(colourPickerButton[i]);
        }
        code.add(colourPicker);

        JButton setCode = new JButton("SET CODE");
        setCode.setFont(new Font("Monospaced", Font.PLAIN, 14));
        setCode.setBackground(Color.DARK_GRAY);
        setCode.setForeground(Color.WHITE);

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

        codeHuman = createCodeHuman(codeColorButtons);

        code.add(colourPicker);
        code.add(codePanel);
        code.add(setCode);

        return code;

    }

    /**
     * The method checks the input for the 'Number of Games' and 'Number of Guesses' text field is a number between 1 and 10
     * @return an int between 1 and 10 that is used to set the text field to the updated and correct number
     * */
    private static int numberValidator(String s) {

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

    /**
     * The method compares the solution code and the guess code to appropriately set the hint 'pegs'
     * @return a Color array that is used to set the hint 'pegs' to the correct colours dependent on the game version
     * */
    private static Color[] compareCode(Color[] g, Color[] s) {

        Color [] h = {Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY};
        int x, y;

        if(getVersion().equals("KIDS")){

            for (x = 0; x < g.length; x++) {
                for (y = 0; y < s.length; y++) {
                    if (g[x] == s[y]) {
                        h[x] = Color.RED;
                        s[y] = Color.GRAY;
                        break;
                    }
                }
            }
        }
        else{
            for(x = 0; x < s.length; x++){
                for(y = 0; y < g.length; y++){
                    if(s[x] == g[y]){
                        if(x == y){
                            h[y] = Color.RED;
                            g[x] = Color.GRAY;
                            break;
                        }
                        else if (y == 4){
                            h[y-1] = Color.BLACK;
                        }
                    }
                }
            }
        }

        return h;
    }

    /**
     * The method that checks if the hint 'pegs' are all set to 'RED' which indicates a win.
     * @return a boolean which determines whether the game has been won.
     * */
    public static boolean checkWin(Color[] h){

        int count = 0;

        for (Color hint : h) {
            if (hint == Color.RED)
                count++;
        }
        return count == 4;
    }

}