package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mastermind extends Game{

    public static void main(String[] args) {

        //CREATE GAME OPTIONS GUI
        JFrame game = new JFrame("MASTERMIND");

        JPanel startGamePanel = new JPanel(new GridLayout(4, 1));
        game.add(startGamePanel);

        startGamePanel.add(createStartButton());
        startGamePanel.add(createLoadButton());
        startGamePanel.add(createViewLeaderboardButton());
        startGamePanel.add(createQuitButton());

        startGamePanel.setFocusable(true);
        //OPTION GUI KEYBOARD
        startGamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S){
                    chooseGameOptions();
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
        game.setSize(400,400);
        game.setResizable(false);
        game.setVisible(true);
    }

    //OPTION GUI BUTTONS
    protected static JButton createQuitButton(){

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

    protected static JButton createStartButton(){

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
    protected static JButton createLoadButton(){

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
            }
        });

        return loadButton;
    }
    public static JButton createViewLeaderboardButton(){

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
            }
        });

        return viewLeaderBoardButton;
    }

    private static void chooseGameOptions(){

        JFrame choiceFrame = new JFrame();
        JPanel optionsPanel = new JPanel(new GridLayout(1,2));

        Font buttonFont = new Font("Monospaced", Font.PLAIN, 18);

        JPanel choicePanelLeft = new JPanel(new GridLayout(6, 1));

        //PLAYER OPTIONS
        JRadioButton singlePlayerButton = new JRadioButton("SINGLE PLAYER");
        singlePlayerButton.setFont(buttonFont);
        singlePlayerButton.setBackground(Color.DARK_GRAY);
        singlePlayerButton.setForeground(Color.WHITE);
        singlePlayerButton.setSelected(true);

        JRadioButton multiPayerButton = new JRadioButton("MULTIPLAYER");
        multiPayerButton.setFont(buttonFont);
        multiPayerButton.setBackground(Color.DARK_GRAY);
        multiPayerButton.setForeground(Color.WHITE);


        //GAME VERSION OPTIONS
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
        playerOneName.setBackground(Color.LIGHT_GRAY);
        playerOneName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        JLabel playerTwoLabel = new JLabel("Enter Player Two:");
        JTextField playerTwoName = new JTextField("Computer");//must be complete to continue
        playerTwoLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerTwoName.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        playerTwoLabel.setForeground(Color.WHITE);
        playerTwoName.setBackground(Color.GRAY);
        playerTwoName.setEditable(false); //set editable if multiplayer

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

        JLabel guessAmountLabel = new JLabel("Enter amount of Guesses (max 10):");
        JTextField guessAmount = new JTextField();//must be complete to continue
        guessAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        guessAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        guessAmount.setBackground(Color.LIGHT_GRAY);
        guessAmountLabel.setForeground(Color.WHITE);

        JLabel gameAmountLabel = new JLabel("Enter amount of Games (max 10):");
        JTextField gameAmount = new JTextField();//must be complete to continue
        gameAmountLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameAmount.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gameAmount.setBackground(Color.LIGHT_GRAY);
        gameAmountLabel.setForeground(Color.WHITE);

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

        gameAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null){
                    numberValidator(e.getActionCommand());
                    //why isn't validation working??
                }
                else
                    JOptionPane.showMessageDialog(null,"Please enter a valid number");
            }
        });

        guessAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null){
                    numberValidator(e.getActionCommand());
                    //why isn't validation working??
                }
                else
                    JOptionPane.showMessageDialog(null,"Please enter a valid number");
            }
        });

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(kidsVersion.isSelected()){

                    if(singlePlayerButton.isSelected()){
                        if(!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {

                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Kid's Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                    else if(multiPayerButton.isSelected()) {
                        if (!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {

                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Kid's Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                }
                else if(classicVersion.isSelected()){
                    if(singlePlayerButton.isSelected()){
                        if(!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {

                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Classic Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                    else if(multiPayerButton.isSelected()) {
                        if (!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {


                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Classic Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                }
                else if(expertVersion.isSelected()){
                    if(singlePlayerButton.isSelected()){
                        if(!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {

                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Expert Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                    else if(multiPayerButton.isSelected()) {
                        if (!gameAmount.getText().isEmpty() && !guessAmount.getText().isEmpty()) {

                            Player playerOne = new Player(playerOneName.getText());
                            Player playerTwo = new Player(playerTwoName.getText());
                            Game newGame = new Game(new Player[]{playerOne, playerTwo}, Integer.parseInt(gameAmount.getText()), Integer.parseInt(guessAmount.getText()), "Expert Version");
                            GameBoardGUI game = new GameBoardGUI(newGame);
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Please select all options", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

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
        choiceFrame.setSize(500,400);
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


