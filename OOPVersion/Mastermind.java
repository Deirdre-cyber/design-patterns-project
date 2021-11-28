package OOPVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mastermind extends JFrame {

    public static void main(String[] args) {

        JFrame game = new JFrame("MASTERMIND");

        JPanel startGamePanel = new JPanel(new GridLayout(4, 1));
        game.add(startGamePanel);

        startGamePanel.add(GameBoardGUI.createStartButton());
        startGamePanel.add(GameBoardGUI.createLoadButton());
        startGamePanel.add(GameBoardGUI.createViewLeaderboardButton());
        startGamePanel.add(GameBoardGUI.createQuitButton());

        startGamePanel.setFocusable(true);

        //how to implement key adapter in definition header again??
        startGamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S){
                    GameBoardGUI.chooseGameOptions();
                }
                else if(e.getKeyCode() == KeyEvent.VK_L){
                    JOptionPane.showMessageDialog(null, "Load Saved game");
                    //load saved game
                }
                else if(e.getKeyCode() == KeyEvent.VK_V){
                    JOptionPane.showMessageDialog(null, "Load Leaderboard");
                    //load
                }
                else if(e.getKeyCode() == KeyEvent.VK_Q){
                    System.exit(0);
                }
            }
        });

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocation(550, 50);
        game.setSize(400,400);
        game.setResizable(false);
        game.setVisible(true);
    }

}
