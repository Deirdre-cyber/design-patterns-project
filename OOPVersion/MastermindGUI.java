package OOPVersion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MastermindGUI extends JFrame{

    public MastermindGUI(){
        super("MASTERMIND");

        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));

        board.add(createOptionBar());
        board.add(Box.createVerticalStrut(30));
        board.add(createGameBoard());


        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550, 0);
        setSize(500,900);
        setVisible(true);
    }

    public static void main(String[] args) {
        MastermindGUI GUI = new MastermindGUI();
    }

    private JPanel createOptionBar(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Serif", Font.PLAIN, 18));
        startButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        startButton.setBackground(Color.WHITE);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton leaderBoardButton = new JButton("LEADERBOARD");
        leaderBoardButton.setFont(new Font("Serif", Font.PLAIN, 18));
        leaderBoardButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        leaderBoardButton.setBackground(Color.LIGHT_GRAY);
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(new Font("Serif", Font.PLAIN, 18));
        quitButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(leaderBoardButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(quitButton);

        return panel;
    }
    private JPanel createGameBoard(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 5));
        //continue

        return panel;

    }

}
