package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameBoardGUI extends JFrame {

    private JButton[] guessButtons;
    private JButton[] colourButtons;
    private JButton[] solutionButtons;      //needed for first run
    private JPanel[] guessPanels;
    private JPanel[] hintPanels;
    private JPanel mainBoard, gameBoard, guessPanel, colourButtonPanel, playPanel;
    private JLabel solutionLabelLeft, solutionLabelRight, gameCounterLabel, hintsLabel;
    private ArrayList<Color> buttonColourList;  //HIDDEN BUTTON
    private JButton makeGuess, blankColour; //preferably add to colourButtons - make arrayLIst
    private int guessEventCount = 0; //make static?
    private final Font GAME_FONT = new Font("Monospaced", Font.PLAIN, 22);

    public GameBoardGUI(){
        super("Welcome Player");

        //create menu panel - quit and save

        mainBoard = new JPanel();
        mainBoard.setLayout(new GridLayout(1,2));   //make better with gridbaglayout

        //mainBoard.add(createMenuBar()); Make it a bar, either second panel or different layout manager.....
        mainBoard.add(createGameBoard());
        mainBoard.add(createPlayBoard());

        add(mainBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550, 50);
        setSize(500,600);
        setResizable(false);
        setVisible(true);
    }

    //GETTERS AND SETTERS - DELETE ALL UNNECESSARY!!!
    public JButton[] getGuessButtons() {
        return guessButtons;
    }
    public void setGuessButtons(JButton[] guessButtons) {
        this.guessButtons = guessButtons;
    }

    public JButton[] getColourButtons() {
        return colourButtons;
    }
    public void setColourButtons(JButton[] colourButtons) {
        this.colourButtons = colourButtons;
    }

    public JPanel[] getGuessPanels() {
        return guessPanels;
    }
    public void setGuessPanels(JPanel[] guessPanels) {
        this.guessPanels = guessPanels;
    }

    public JPanel[] getHintPanels() {
        return hintPanels;
    }
    public void setHintPanels(JPanel[] hintPanels) {
        this.hintPanels = hintPanels;
    }

    public JPanel getMainBoard() {
        return mainBoard;
    }
    public void setMainBoard(JPanel mainBoard) {
        this.mainBoard = mainBoard;
    }

    public JPanel getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(JPanel gameBoard) {
        this.gameBoard = gameBoard;
    }

    public JPanel getGuessPanel() {
        return guessPanel;
    }
    public void setGuessPanel(JPanel guessPanel) {
        this.guessPanel = guessPanel;
    }

    public JPanel getColourButtonPanel() {
        return colourButtonPanel;
    }
    public void setColourButtonPanel(JPanel colourButtonPanel) {
        this.colourButtonPanel = colourButtonPanel;
    }

    public JPanel getPlayPanel() {
        return playPanel;
    }
    public void setPlayPanel(JPanel playPanel) {
        this.playPanel = playPanel;
    }

    public JLabel getSolutionLabelLeft() {
        return solutionLabelLeft;
    }
    public void setSolutionLabelLeft(JLabel solutionLabelLeft) {
        this.solutionLabelLeft = solutionLabelLeft;
    }

    public JLabel getSolutionLabelRight() {
        return solutionLabelRight;
    }
    public void setSolutionLabelRight(JLabel solutionLabelRight) {
        this.solutionLabelRight = solutionLabelRight;
    }

    public JLabel getGameCounterLabel() {
        return gameCounterLabel;
    }
    public void setGameCounterLabel(JLabel gameCounterLabel) {
        this.gameCounterLabel = gameCounterLabel;
    }

    public JLabel getHintsLabel() {
        return hintsLabel;
    }
    public void setHintsLabel(JLabel hintsLabel) {
        this.hintsLabel = hintsLabel;
    }

    public ArrayList<Color> getButtonColourList() {
        return buttonColourList;
    }
    public void setButtonColourList(ArrayList<Color> buttonColourList) {
        this.buttonColourList = buttonColourList;
    }

    public JButton getMakeGuess() {
        return makeGuess;
    }
    public void setMakeGuess(JButton makeGuess) {
        this.makeGuess = makeGuess;
    }

    public JButton getBlankColour() {
        return blankColour;
    }
    public void setBlankColour(JButton blankColour) {
        this.blankColour = blankColour;
    }

    public int getGuessEventCount() {
        return guessEventCount;
    }
    public void setGuessEventCount(int guessEventCount) {
        this.guessEventCount = guessEventCount;
    }

    public Font getGameFont() {
        return GAME_FONT;
    }

    //--------MENU BAR--------------
    /*private JPanel createMenuBar(){

        JPanel menuPanel = new JPanel(new FlowLayout());
        JMenuBar mainMenuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenuItem saveItem = new JMenuItem("SAVE");
        JMenuItem quitItem = new JMenuItem("QUIT");

        gameMenu.add(saveItem);
        gameMenu.add(quitItem);

        mainMenuBar.add(gameMenu);

        menuPanel.add(mainMenuBar);

        return menuPanel;
    }*/


    private JPanel createGameBoard(){

        gameBoard = new JPanel(new GridLayout(1, 2));

        //RIGHT PANEL
        JPanel panelLeft = new JPanel(new GridLayout(12, 1));
        panelLeft.setBackground(Color.GRAY);

        //----------TO BE SHOWN WHILE PLAYER ONE SELECTING (MULTIPLAYER)-----------
        /*JPanel solutionPanel = new JPanel();

        solutionButtons = new JButton[4]; //set by createCode() in Player

        for(int j = 0; j < solutionButtons.length; j++) {
            //add graphics - 4 circle lines...new class or method : paintComponent
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
        solutionLabelLeft = new JLabel("MASTER");//if two players, player enters solution first.Then remove solution and add JLabel
        solutionLabelLeft.setFont(GAME_FONT);
        solutionLabelLeft.setForeground(Color.WHITE);
        solutionLabelLeft.setHorizontalAlignment(SwingConstants.RIGHT);

        panelLeft.add(solutionLabelLeft);

        //----------------each panel must have 4 blank buttons/labels than can be changes----------------

        guessPanels = new JPanel[10]; //Number is numGuesses chosen

        for(int i = 0; i < guessPanels.length; i++){
            guessPanels[i] = new JPanel(new FlowLayout());
            guessPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            guessPanels[i].setBackground(Color.WHITE);
            panelLeft.add(guessPanels[i]);
            //add graphics - 4 circle lines - to each panel...new class or method : paintComponent
        }

        gameCounterLabel = new JLabel("Game No.1"); //set by numGames : after game play "You win/you lose"
        gameCounterLabel.setFont(GAME_FONT);
        gameCounterLabel.setForeground(Color.WHITE);
        gameCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelLeft.add(gameCounterLabel);

        //RIGHT PANEL
        JPanel panelRight = new JPanel(new GridLayout(12, 1));
        panelRight.setBackground(Color.GRAY);

        solutionLabelRight = new JLabel("MIND");        //use GridBagLayout?
        solutionLabelRight.setFont(GAME_FONT);
        solutionLabelRight.setForeground(Color.WHITE);
        solutionLabelRight.setHorizontalAlignment(SwingConstants.LEFT);
        panelRight.add(solutionLabelRight);

        hintPanels = new JPanel[10]; //Number is numGames chosen

        for(int i = 0; i < hintPanels.length; i++){
            hintPanels[i] = new JPanel(new FlowLayout());
            hintPanels[i].add(new JLabel("O O O O"));
            hintPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panelRight.add(hintPanels[i]);
            //add graphics - 4 circle lines - to each panel...new class or method : paintComponent
        }

        hintsLabel = new JLabel("hints"); //set by numGames
        hintsLabel.setFont(GAME_FONT);
        hintsLabel.setForeground(Color.WHITE);
        hintsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight.add(hintsLabel);

        gameBoard.add(panelLeft);
        gameBoard.add(panelRight);

        return gameBoard;
    }
    private JPanel createPlayBoard(){

        playPanel = new JPanel(new GridLayout(5, 1));
        playPanel.setBackground(Color.GRAY);

        JLabel placeholder = new JLabel("   ");
        playPanel.add(placeholder);

        colourButtonPanel = new JPanel(new GridLayout(4, 2));
        colourButtonPanel.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));

        colourButtons = new JButton[8];

        buttonColourList = new ArrayList<>();
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
                        //else if condition for when solution being made
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

        /*//add hidden button code
        blankColour = new JButton("[blank]");
        blankColour.setBackground(Color.GRAY);
        blankColour.setForeground(Color.WHITE);
        colourButtonPanel.add(blankColour);*/

        playPanel.add(colourButtonPanel);

        JLabel placeholder2 = new JLabel("  ");
        playPanel.add(placeholder2);

        guessPanel = new JPanel(new GridBagLayout());
        guessPanel.setBackground(Color.GRAY);
        guessButtons = new JButton[4];

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

        makeGuess = new JButton("Make Guess");
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

    protected static JButton createQuitButton(){

        JButton quitButton = new JButton("[Q]UIT");
        quitButton.setFont(new Font("Monospaced", Font.BOLD, 18)); //getFont from gui
        quitButton.setBackground(Color.BLUE);
        quitButton.setForeground(Color.BLACK);
        quitButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        return quitButton;
    }

    protected static JButton createStartButton(){

        JButton startButton = new JButton("[S]TART NEW GAME");
        startButton.setFont(new Font("Monospaced", Font.BOLD, 18)); //getFont from gui somehow?? before game is loaded....
        startButton.setBackground(Color.ORANGE);
        startButton.setForeground(Color.BLACK);
        startButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameBoardGUI.chooseGameOptions();
            }
        });

        return startButton;
    }
    protected static JButton createLoadButton(){

        JButton loadButton = new JButton("[L]OAD GAME");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 18)); //getFont from gui
        loadButton.setBackground(Color.GREEN);
        loadButton.setForeground(Color.BLACK);
        loadButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameBoardGUI newGame = new GameBoardGUI();
                //open saved game files
            }
        });

        return loadButton;
    }
    public static JButton createViewLeaderboardButton(){

        JButton viewLeaderBoardButton = new JButton("[V]IEW LEADERBOARD");
        viewLeaderBoardButton.setFont(new Font("Monospaced", Font.BOLD, 18)); //getFont from gui
        viewLeaderBoardButton.setBackground(Color.CYAN);
        viewLeaderBoardButton.setForeground(Color.BLACK);
        viewLeaderBoardButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        viewLeaderBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.print("View Leaderboard");
                //open saves files
            }
        });

        return viewLeaderBoardButton;
    }

    public static JButton createGameOptionsButton(){

        JButton gameOptionsButton = new JButton("[G]AME OPTIONS...");
        gameOptionsButton.setFont(new Font("Monospaced", Font.BOLD, 18)); //getFont from gui??
        gameOptionsButton.setBackground(Color.MAGENTA);
        gameOptionsButton.setForeground(Color.BLACK);
        gameOptionsButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.GRAY));

        gameOptionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseGameOptions();
                //create player objects
                //set difficulty version
            }
        });

        return gameOptionsButton;
    }
    public static void chooseGameOptions(){

        //player + game difficulty
        JFrame choiceFrame = new JFrame();
        JPanel optionsPanel = new JPanel(new GridLayout(1,2));

        Font buttonFont = new Font("Monospaced", Font.PLAIN, 18);

        JPanel choicePanelLeft = new JPanel(new GridLayout(6, 1));

        JRadioButton singlePlayerButton = new JRadioButton("SINGLE PLAYER");
        singlePlayerButton.setFont(buttonFont);
        singlePlayerButton.setBackground(Color.DARK_GRAY);
        singlePlayerButton.setForeground(Color.WHITE);
        singlePlayerButton.setSelected(true);

        JRadioButton multiPayerButton = new JRadioButton("MULTIPLAYER");
        multiPayerButton.setFont(buttonFont);
        multiPayerButton.setBackground(Color.DARK_GRAY);
        multiPayerButton.setForeground(Color.WHITE);

        JRadioButton kidsVersion = new JRadioButton("EASY");
        kidsVersion.setFont(buttonFont);
        kidsVersion.setBackground(Color.DARK_GRAY);
        kidsVersion.setForeground(Color.WHITE);
        kidsVersion.setSelected(true);

        JRadioButton classicVersion = new JRadioButton("CLASSIC");
        classicVersion.setFont(buttonFont);
        classicVersion.setBackground(Color.DARK_GRAY);
        classicVersion.setForeground(Color.WHITE);

        JRadioButton expertVersion = new JRadioButton("EXPERT");
        expertVersion.setFont(buttonFont);
        expertVersion.setBackground(Color.DARK_GRAY);
        expertVersion.setForeground(Color.WHITE);

        JButton confirmButton = new JButton("START GAME");
        confirmButton.setFont(buttonFont);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.DARK_GRAY));
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //once all valiation is done
                GameBoardGUI newgame = new GameBoardGUI();
            }
        });

        choicePanelLeft.add(singlePlayerButton);
        choicePanelLeft.add(multiPayerButton);
        choicePanelLeft.add(kidsVersion);
        choicePanelLeft.add(classicVersion);
        choicePanelLeft.add(expertVersion);
        choicePanelLeft.add(confirmButton);

        JPanel choicePanelRight = new JPanel(new GridLayout(12,1));

        JLabel playerOneLabel = new JLabel("Enter Player One:");
        JTextField playerOneName = new JTextField();//must be complete to continue if multi play
        playerOneLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerOneLabel.setForeground(Color.WHITE);
        playerOneName.setBackground(Color.GRAY);
        playerOneName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));


        JLabel playerTwoLabel = new JLabel("Enter Player Two:");
        JTextField playerTwoName = new JTextField();//must be complete to continue
        playerTwoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerTwoName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        playerTwoLabel.setForeground(Color.WHITE);
        playerOneName.setBackground(Color.GRAY);
        playerOneName.setEditable(false); //set editable if multiplayer

        JLabel guessAmountLabel = new JLabel("Enter amount of Guesses (max 10):");
        JTextField guessAmount = new JTextField();//must be complete to continue
        guessAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmountLabel.setForeground(Color.WHITE);
        //validation code for textfield....

        JLabel gameAmountLabel = new JLabel("Enter amount of Games (max 10):");
        JTextField gameAmount = new JTextField();//must be complete to continue
        gameAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmountLabel.setForeground(Color.WHITE);
        //validation code for textfield....

        choicePanelRight.add(playerOneLabel);
        choicePanelRight.add(playerOneName);
        choicePanelRight.add(playerTwoLabel);
        choicePanelRight.add(playerTwoName);
        choicePanelRight.add(gameAmountLabel);
        choicePanelRight.add(gameAmount);
        choicePanelRight.add(guessAmountLabel);
        choicePanelRight.add(guessAmount);

        choicePanelLeft.setBackground(Color.LIGHT_GRAY);
        choicePanelRight.setBackground(Color.DARK_GRAY);

        optionsPanel.add(choicePanelLeft);
        optionsPanel.add(choicePanelRight);

        choiceFrame.add(optionsPanel);

        choiceFrame.setLocation(550, 50);
        choiceFrame.setSize(600,400);
        choiceFrame.setResizable(false);
        choiceFrame.setVisible(true);

    }
}
