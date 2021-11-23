package OOPVersion;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame{

    public BoardGUI(){
        super("MASTERMIND");

        BorderLayout layout = new BorderLayout(5,5);
        setLayout(layout);

        JPanel topPanel = new JPanel();

        JPanel middlePanel = new JPanel(new GridLayout(10, 4));

        JButton startButton = new JButton("START GAME");
        JButton leaderBoardButton = new JButton("LEADERBOARD");
        JButton quitButton = new JButton("QUIT");

        topPanel.add(startButton);
        topPanel.add(leaderBoardButton);
        topPanel.add(quitButton);


    }

}
