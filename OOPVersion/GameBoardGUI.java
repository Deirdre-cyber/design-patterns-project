package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoardGUI extends JFrame /*, KeyListener*/ {

    private JButton[] guessButtons;
    private JPanel colourButtonPanel, playPanel;
    private int guessEventCount=0;

    public GameBoardGUI(){
        super("MASTERMIND");

        JPanel mainBoard = new JPanel();
        mainBoard.setLayout(new GridLayout(1,2));   //make better with gridbaglayout

        mainBoard.add(createGameBoard());
        mainBoard.add(createPlayBoard());

        add(mainBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550, 50);
        setSize(500,600);
        setResizable(false);
        setVisible(true);
    }
    //GETTERS AND SETTERS


    private JPanel createGameBoard(){

        JPanel gameBoard = new JPanel(new GridLayout(1, 2));

        //RIGHT PANEL
        JPanel panelLeft = new JPanel(new GridLayout(12, 1));
        //panelLeft.setSize(300, 500);
        panelLeft.setBackground(Color.GRAY);

        JLabel solutionLabel = new JLabel("MASTER");//change to solution graphic if game won : if-else
        solutionLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        solutionLabel.setForeground(Color.WHITE);
        solutionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        panelLeft.add(solutionLabel);

        JPanel[] guessPanels = new JPanel[10]; //Number is numGames chosen

        for(int i = 0; i < guessPanels.length; i++){
            guessPanels[i] = new JPanel(new FlowLayout());
            guessPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            guessPanels[i].setBackground(Color.WHITE);
            panelLeft.add(guessPanels[i]);
            //add graphics - 4 circle lines - to each panel...new class or method : paintComponent
        }

        JLabel gameCounter = new JLabel("Game No.1"); //set by numGames : after game play "You win/you lose"
        gameCounter.setFont(new Font("Monospaced", Font.PLAIN, 18));
        gameCounter.setForeground(Color.WHITE);
        gameCounter.setHorizontalAlignment(SwingConstants.CENTER);

        panelLeft.add(gameCounter);

        //RIGHT PANEL
        JPanel panelRight = new JPanel(new GridLayout(12, 1));
        panelRight.setBackground(Color.GRAY);

        JLabel solutionLabel2 = new JLabel("MIND");
        solutionLabel2.setFont(new Font("Monospaced", Font.BOLD, 22));
        solutionLabel2.setForeground(Color.WHITE);
        solutionLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        panelRight.add(solutionLabel2);

        JPanel[] hintPanels = new JPanel[10]; //Number is numGames chosen

        for(int i = 0; i < hintPanels.length; i++){
            hintPanels[i] = new JPanel(new FlowLayout());
            hintPanels[i].add(new JLabel("O O O O"));
            hintPanels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panelRight.add(hintPanels[i]);
            //add graphics - 4 circle lines - to each panel...new class or method : paintComponent
        }

        JLabel hintsLabel = new JLabel("hints"); //set by numGames
        hintsLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
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

        JButton[] colourButtons = new JButton[8];
        Color[] buttonColours = {Color.WHITE, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, Color.PINK, Color.CYAN, Color.ORANGE};

        for(int i = 0; i < colourButtons.length; i++){
            colourButtons[i] = new JButton();
            colourButtons[i].setBackground(buttonColours[i]);
            colourButtons[i].setForeground(buttonColours[i]);
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

        playPanel.add(colourButtonPanel);

        JLabel placeholder2 = new JLabel("  ");
        playPanel.add(placeholder2);

        JPanel guessPanel = new JPanel(new GridBagLayout());
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
            //add listeners if pressed button resets colour
        }

        playPanel.add(guessPanel);

        JButton makeGuess = new JButton("Make Guess");
        makeGuess.setBackground(Color.WHITE);
        makeGuess.setFont(new Font("Monospaced", Font.BOLD, 18));
        makeGuess.setBackground(Color.GRAY);
        makeGuess.setForeground(Color.WHITE);
        makeGuess.setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.WHITE));
        makeGuess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(guessEventCount == 4){      //guess event must be correct
                    JOptionPane.showMessageDialog(null, "Guess made");
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
