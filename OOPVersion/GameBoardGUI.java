package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoardGUI extends JFrame /*implements MouseListener, KeyListener*/ {

    public GameBoardGUI(){
        super("MASTERMIND");

        JPanel mainBoard = new JPanel();
        mainBoard.setLayout(new GridLayout(1,2));

        mainBoard.add(createGameBoard());
        mainBoard.add(createPlayBoard());

        add(mainBoard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550, 50);
        setSize(500,600);
        setResizable(false);
        setVisible(true);
    }
    private JPanel createGameBoard(){

        JPanel gameBoard = new JPanel(new GridLayout(1, 2));

        //RIGHT PANEL
        JPanel panelLeft = new JPanel(new GridLayout(12, 1));
        //panelLeft.setSize(300, 500);      how to set size?
        panelLeft.setBackground(Color.LIGHT_GRAY);

        JLabel solutionLabel = new JLabel("Master");//change to solution graphic if game won : if-else
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
        panelRight.setBackground(Color.LIGHT_GRAY);

        JLabel placeHolder = new JLabel("mind");
        placeHolder.setFont(new Font("Monospaced", Font.BOLD, 22));
        placeHolder.setForeground(Color.WHITE);
        placeHolder.setHorizontalAlignment(SwingConstants.LEFT);
        panelRight.add(placeHolder);

        JPanel[] hintPanels = new JPanel[10]; //Number is numGames chosen

        for(int i = 0; i < hintPanels.length; i++){
            hintPanels[i] = new JPanel(new FlowLayout());
            hintPanels[i].add(new JLabel("0 0 0 0"));
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(100, 600);
        panel.setBackground(Color.ORANGE);



        return panel;
    }
}
