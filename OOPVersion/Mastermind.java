package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Mastermind extends Game implements Serializable{

    private static int playButtonEventCount;
    private static Player playerOne;
    private static Player playerTwo;
    private static int numGuesses;
    private static int numGames;
    private static int guessEventCount;
    private static final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);
    private ArrayList<Color> Colours;

    public Mastermind(Player[] players, int numberGames, int numberGuesses, String version) {
        super(players, numberGames, numberGuesses, version);
    }

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

                //open saves leaderboard files - serialisation
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
        confirmPlayerButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));

        //Confirm player options mouse listener
        confirmPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //validate : PLayer One text box must not be empty
                if(!playerOneName.getText().equals("")){
                    playerOne = new Player(playerOneName.getText());
                    playerTwo = new Player(playerTwoName.getText());

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

        //Create Guess Amount Text Box
        JTextField guessAmount = new JTextField();
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmount.setBackground(Color.LIGHT_GRAY);

        //Create Guess Amount Confirm Button
        JButton addGuessAmountButton = new JButton("Confirm Number of Games");
        addGuessAmountButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        addGuessAmountButton.setForeground(Color.WHITE);
        addGuessAmountButton.setBackground(Color.LIGHT_GRAY);

        addGuessAmountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!guessAmount.getText().equals("")){
                    numGuesses = numberValidator(guessAmount.getText());
                    guessAmount.setText(String.format("%d", numGuesses));
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
        JTextField gameAmount = new JTextField();//must be complete to continue
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmount.setBackground(Color.LIGHT_GRAY);

        //Create Game Amount Confirm Button
        JButton addGameAmountButton = new JButton("Confirm Number of Games");
        addGameAmountButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        addGameAmountButton.setForeground(Color.WHITE);
        addGameAmountButton.setBackground(Color.LIGHT_GRAY);

        addGameAmountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!gameAmount.getText().equals("")){
                    numGames = numberValidator(gameAmount.getText());
                    gameAmount.setText(String.format("%d", numGames));
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
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));

        //Create 'START GAME' Button
        JButton playGame = new JButton("START GAME");
        playGame.setFont(buttonFont);
        playGame.setBackground(Color.CYAN);
        playGame.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));
        playGame.setVisible(false);

        //Once all validated open GAMEBOARD - details needed [name(s), numguesses, numgames]
        playGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //CREATE GAME BOARD GUI - REMADE EACH GAME : loop number of games

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

                //once finished ask to save game
                //add to leaderboard
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //improve this....

                if(kidsVersion.isSelected() && playButtonEventCount == 1){

                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Kid's Version");

                    //test code - REMOVE
                    System.out.print(newGame);

                    confirmButton.setVisible(false);
                    playGame.setVisible(true);
                }
                else if(classicVersion.isSelected() && playButtonEventCount == 1){

                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Classic Version");

                    //test code - REMOVE
                    System.out.print(newGame);

                    confirmButton.setVisible(false);
                    playGame.setVisible(true);

                }
                else if(kidsVersion.isSelected() && playButtonEventCount == 1){

                    Game newGame = new Game(new Player[]{playerOne, playerTwo}, numGames, numGuesses, "Expert Version");

                    //test code - REMOVE
                    System.out.print(newGame);
                    //show play game button which launches GUI

                    confirmButton.setVisible(false);
                    playGame.setVisible(true);
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

    private static JPanel createGameBoard(){

        //CREATE GAMEBOARD
        JPanel gameBoard = new JPanel(new GridLayout(1, 2));

        //CREATE LEFT PANEL - GUESSES AND HINTS
        JPanel panelLeft = new JPanel(new GridLayout(12, 1));
        panelLeft.setBackground(Color.GRAY);

        if(playerTwo.getPlayer().equals("Computer")){
            System.out.print(playerTwo);
        }
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

        JLabel gameCounterLabel = new JLabel("Game No" + numGames); //after game play "You win/you lose"
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

    private static JPanel createPlayBoard(){


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

            /*colourButtons[i].addMouseListener(new MouseAdapter() {
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
            colourButtonPanel.add(colourButtons[i]);*/
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
    }
}


